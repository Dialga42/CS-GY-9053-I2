package finalproject.server;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import finalproject.db.DBInterface;
import finalproject.entities.Person;

public class Server extends JFrame implements Runnable {

    public static final int DEFAULT_PORT = 8001;
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 800;
    final int AREA_ROWS = 10;
    final int AREA_COLUMNS = 40;

    JMenuBar menuBar;
    JMenu menu;

    JPanel topPanel;
    JLabel dbLabel;
    JButton queryButton;
    JTextArea textArea;

    private Connection conn;
    private PreparedStatement queryStmt;

    private int clientNo = 0;

    public Server() throws IOException, SQLException {
        this(DEFAULT_PORT, "server.db");
    }

    public Server(String dbFile) throws IOException, SQLException {
        this(DEFAULT_PORT, dbFile);
    }

    public Server(int port, String dbFile) throws IOException, SQLException {

        this.setSize(Server.FRAME_WIDTH, Server.FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createMenus();
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(0, 1));
        dbLabel = new JLabel("DB: " + dbFile);
        JPanel p1 = new JPanel();
        p1.add(dbLabel);
        topPanel.add(p1);
        queryButton = new JButton("Query DB");
        queryButton.addActionListener(new QueryButtonListener());
        JPanel p2 = new JPanel();
        p2.add(queryButton);
        topPanel.add(p2);
        textArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(250, 80));
        this.add(topPanel, BorderLayout.NORTH);
        this.add(scrollPane);

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:Server.db");
            queryStmt = conn.prepareStatement("Select * from People");
        } catch (SQLException e) {
            System.err.println("Connection error: " + e);
            System.exit(1);
        }
        Thread t = new Thread(this);
        t.start();
    }

    class QueryButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                ResultSet rset = queryStmt.executeQuery();
                ResultSetMetaData rsmd = rset.getMetaData();
                String rowString = "DB Results:\n" +
                        "first\tlast\tage\tcity\tsent\tid\n" +
                        "-----\t----\t---\t----\t----\t--\n";
                int numColumns = rsmd.getColumnCount();
                while (rset.next()) {
                    for (int i = 1; i <= numColumns; i++) {
                        Object o = rset.getObject(i);
                        rowString += o.toString() + "\t";
                    }
                    rowString += "\n";
                }
                textArea.append(rowString);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void createMenus() {
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menu.add(createFileExitItem());
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    public JMenuItem createFileExitItem() {
        JMenuItem item = new JMenuItem("Exit");
        item.addActionListener((e) -> System.exit(0));
        return item;
    }

    public static void main(String[] args) {

        Server sv;
        try {
            sv = new Server("server.db");
            sv.setVisible(true);
        } catch (IOException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8001);
            textArea.append("Listening on port 8001\n");

            while (true) {
                // Listen for a new connection request
                Socket socket = serverSocket.accept();

                // Increment clientNo
                clientNo++;

                textArea.append("Starting thread for client " + clientNo +
                        " at " + new Date() + '\n');

                // Find the client's host name, and IP address
                InetAddress inetAddress = socket.getInetAddress();
                textArea.append("Client " + clientNo + "'s host name is "
                        + inetAddress.getHostName() + "\n");
                textArea.append("Client " + clientNo + "'s IP Address is "
                        + inetAddress.getHostAddress() + "\n");

                // Create and start a new thread for the connection
                new Thread(new HandleAClient(socket, clientNo)).start();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    class HandleAClient implements Runnable {
        private Socket socket; // A connected socket
        private int clientNum;

        /**
         * Construct a thread
         */
        public HandleAClient(Socket socket, int clientNum) {
            this.socket = socket;
            this.clientNum = clientNum;
        }

        /**
         * Run a thread
         */
        public void run() {
            try {
                // Create data input and output streams
                DataOutputStream outputToClient = new DataOutputStream(
                        socket.getOutputStream());
                textArea.append("Listening for input from client " + clientNo + "\n");
                // Continuously serve the client
                while (true) {
                    Person now;
                    try {
                        ObjectInputStream inputFromClient = new ObjectInputStream(
                                socket.getInputStream());
                        now = (Person) inputFromClient.readObject();
                        textArea.append("got person " + now.toString());
                        PreparedStatement insertData = conn.prepareStatement("Insert Into People (last,first,age,city,sent,id) " +
                                "Values (?,?,?,?,?,?)");
                        insertData.setString(1, now.getLast());
                        insertData.setString(2, now.getFirst());
                        insertData.setInt(3, now.getAge());
                        insertData.setString(4, now.getCity());
                        insertData.setInt(5, 1);
                        insertData.setInt(6, now.getId());
                        textArea.append("inserting into db\n");
                        synchronized (this) {
                            insertData.execute();
                        }
                        textArea.append("Inserted successfully\n");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        outputToClient.writeUTF("Failed\n");
                        textArea.append("IO Error: null. Ending connection\n");
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        outputToClient.writeUTF("Failed\n");
                        break;
                    }
                    outputToClient.writeUTF("Success\n");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
