package finalproject.client;

import java.awt.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

import finalproject.client.ClientInterface.ComboBoxItem;
import finalproject.db.DBInterface;
import finalproject.entities.Person;
import finalproject.server.Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientInterface extends JFrame {

    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_PORT = 8001;

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 400;
    final int AREA_ROWS = 10;
    final int AREA_COLUMNS = 40;

    JComboBox peopleSelect;
    JFileChooser jFileChooser;
    Socket socket;
    int port;

    JPanel topPanel;
    JLabel activeDB;
    JLabel dbName;
    JLabel activeConnection;
    JLabel connectionName;
    JButton openConnection;
    JButton closeConnection;
    JButton sendData;
    JButton queryDBData;
    JTextArea textArea;

    private Connection conn;
    private PreparedStatement queryStmt;

    public ClientInterface() {
        this(DEFAULT_PORT);
    }

    public ClientInterface(int port) {
        this.port = port;
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        this.setJMenuBar(menuBar);
        jFileChooser = new JFileChooser(".");

        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(0, 1));

        JPanel panel1 = new JPanel();
        activeDB = new JLabel("Active DB: ");
        dbName = new JLabel("<None>");
        panel1.add(activeDB);
        panel1.add(dbName);
        topPanel.add(panel1);

        JPanel panel2 = new JPanel();
        activeConnection = new JLabel("Active Connection: ");
        connectionName = new JLabel("<None>");
        panel2.add(activeConnection);
        panel2.add(connectionName);
        topPanel.add(panel2);

        JPanel panel3 = new JPanel();
        peopleSelect = new JComboBox();
        peopleSelect.addItem("<Empty>");
        panel3.add(peopleSelect);
        topPanel.add(panel3);

        JPanel panel4 = new JPanel();
        openConnection = new JButton("Open Connection");
        openConnection.addActionListener(new OpenConnectionListener());
        closeConnection = new JButton("Close Connection");
        closeConnection.addActionListener(new CloseConnectionListener());
        panel4.add(openConnection);
        panel4.add(closeConnection);
        topPanel.add(panel4);

        JPanel panel5 = new JPanel();
        sendData = new JButton("Send Data");
        sendData.addActionListener(new SendButtonListener());
        queryDBData = new JButton("Query DB Data");
        queryDBData.addActionListener(new QueryButtonListener());
        panel5.add(sendData);
        panel5.add(queryDBData);
        topPanel.add(panel5);

        textArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(250, 80));
        this.add(topPanel, BorderLayout.NORTH);
        this.add(scrollPane);
    }

    public JMenu createFileMenu() {
        JMenu menu = new JMenu("File");
        menu.add(createFileOpenItem());
        menu.add(createFileExitItem());
        return menu;
    }

    public JMenuItem createFileExitItem() {
        JMenuItem item = new JMenuItem("Exit");
        item.addActionListener((e) -> System.exit(0));
        return item;
    }

    private void fillComboBox() throws SQLException {
        List<ComboBoxItem> l = getNames();
        peopleSelect.setModel(new DefaultComboBoxModel(l.toArray()));
    }

    private JMenuItem createFileOpenItem() {
        JMenuItem item = new JMenuItem("Open DB");
        item.addActionListener(new OpenDBListener());
        return item;
    }

    class OpenConnectionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                socket = new Socket("localhost", 8001);
                connectionName.setText("localhost:8001");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class CloseConnectionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                socket.close();
                connectionName.setText("<None>");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class SendButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {

                // responses are going to come over the input as text, and that's tricky,
                // which is why I've done that for you:
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // now, get the person on the object dropdownbox we've selected
                ComboBoxItem personEntry = (ComboBoxItem) peopleSelect.getSelectedItem();

                // That's tricky which is why I have included the code. the personEntry
                // contains an ID and a name. You want to get a "Person" object out of that
                // which is stored in the database
                ResultSet rset = conn.prepareStatement("Select * from People where id=" + personEntry.id).executeQuery();
                Person selectedPerson = new Person();
                while (rset.next()) {
                    String first = rset.getObject(1).toString(),
                            last = rset.getObject(2).toString(),
                            city = rset.getObject(4).toString();
                    int id = Integer.valueOf(rset.getObject(6).toString()),
                            age = Integer.valueOf(rset.getObject(3).toString());
                    selectedPerson = new Person(first, last, age, city, id);
                }
                // Send the person object here over an output stream that you got from the socket.
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject((Object) selectedPerson);
                out.flush();
                String response = br.readLine();
                if (response.contains("Success")) {
                    System.out.println("Success");
                    // what do you do after we know that the server has successfully
                    // received the data and written it to its own database?
                    // you will have to write the code for that.
                    conn.prepareStatement("UPDATE People SET sent=1 where id=" + personEntry.id).execute();
                    peopleSelect.removeAllItems();
                    fillComboBox();
                } else {
                    System.out.println("Failed");
                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


        }

    }

    class QueryButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                ResultSet rset = conn.prepareStatement("Select * from People").executeQuery();
                ResultSetMetaData rsmd = rset.getMetaData();
                String rowString = "first\tlast\tage\tcity\tsent\tid\n" +
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

    private List<ComboBoxItem> getNames() throws SQLException {
        List<ComboBoxItem> res = new LinkedList<>();
        ResultSet rset = conn.prepareStatement("Select * from People where sent=0").executeQuery();
        while (rset.next()) {
            String name = rset.getObject(1).toString() + " " + rset.getObject(2).toString();
            int id = Integer.valueOf(rset.getObject(6).toString());
            res.add(new ComboBoxItem(id, name));
        }
        return res;
    }

    // a JComboBox will take a bunch of objects and use the "toString()" method
// of those objects to print out what's in there.
// So I have provided to you an object to put people's names and ids in
// and the combo box will print out their names.
// now you will want to get the ComboBoxItem object that is selected in the combo box
// and get the corresponding row in the People table and make a person object out of that.
    class ComboBoxItem {
        private int id;
        private String name;

        public ComboBoxItem(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public String toString() {
            return this.name;
        }
    }

    /* the "open db" menu item in the client should use this ActionListener */
    class OpenDBListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int returnVal = jFileChooser.showOpenDialog(getParent());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " + jFileChooser.getSelectedFile().getAbsolutePath());
                String dbFileName = jFileChooser.getSelectedFile().getAbsolutePath();
                try {
                    /* now that you have the dbFileName, you should probably connect to the DB */
                    /* maybe think about filling the contents of the dropdown box listing names
                     * and indicating the name of the Active DB
                     */
                    conn = DriverManager.getConnection("jdbc:sqlite:" + dbFileName);
                    dbName.setText(dbFileName.substring(dbFileName.lastIndexOf("/") + 1));
                    peopleSelect.removeAllItems();
                    fillComboBox();
                } catch (Exception e) {
                    System.err.println("error connection to db: " + e.getMessage());
                    e.printStackTrace();
                    dbName.setText("<None>");
                    peopleSelect.removeAllItems();
                    peopleSelect.addItem("<Empty>");
                }

            }
        }

    }

    public static void main(String[] args) {
        ClientInterface ci = new ClientInterface();
        ci.setVisible(true);
    }
}
