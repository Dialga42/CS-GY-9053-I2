import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class JDBCInterface extends JFrame {

    private JPanel controlPanel;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField ageField;
    private JTextField cityField;
    private JTextArea textQueryArea;
    private JButton insertButton;
    private JTextField lastNameQuery;
    private JButton queryButton;

    private Connection conn;
    private PreparedStatement queryStmtLastName;

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 400;
    final int AREA_ROWS = 20;
    final int AREA_COLUMNS = 40;

    public JDBCInterface() {


        try {
            conn = DriverManager.getConnection("jdbc:sqlite:assignment.db");
            queryStmtLastName = conn.prepareStatement("Select * from People WHERE Last = ?");

        } catch (SQLException e) {
            System.err.println("Connection error: " + e);
            System.exit(1);
        }

        createControlPanel();
        queryButton.addActionListener(new QueryButtonListener());
        insertButton.addActionListener(new InsertButtonListener());

        textQueryArea = new JTextArea(
                AREA_ROWS, AREA_COLUMNS);
        textQueryArea.setEditable(false);

        /* scrollPanel is optional */
        JScrollPane scrollPane = new JScrollPane(textQueryArea);
        JPanel textPanel = new JPanel();
        textPanel.add(scrollPane);
        this.add(textPanel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.NORTH);
    }

    private JPanel createControlPanel() {

        /* you are going to have to create a much more fully-featured layout */

        controlPanel = new JPanel();

        controlPanel.setLayout(new GridLayout(0, 2));
        JPanel lastName = new JPanel();
        lastName.add(new JLabel("Last Name:"));
        lastNameField = new JTextField(10);
        lastName.add(lastNameField);

        JPanel firstName = new JPanel();
        firstName.add(new JLabel("First Name:"));
        firstNameField = new JTextField(10);
        firstName.add(firstNameField);

        JPanel age = new JPanel();
        age.add(new JLabel("age:"));
        ageField = new JTextField(4);
        age.add(ageField);

        JPanel city = new JPanel();
        city.add(new JLabel("city:"));
        cityField = new JTextField(10);
        city.add(cityField);

        JPanel buttonPanel1 = new JPanel();
        insertButton = new JButton("Insert");
        buttonPanel1.add(insertButton);

        JPanel emptyPanel = new JPanel();

        JPanel inputPanel = new JPanel();
        JLabel lbl = new JLabel("Last Name:");
        inputPanel.add(lbl);
        lastNameQuery = new JTextField(10);
        inputPanel.add(lastNameQuery);

        JPanel buttonPanel = new JPanel();
        queryButton = new JButton("Execute Query");
        buttonPanel.add(queryButton);

        controlPanel.add(lastName);
        controlPanel.add(firstName);
        controlPanel.add(age);
        controlPanel.add(city);
        controlPanel.add(buttonPanel1);
        controlPanel.add(emptyPanel);
        controlPanel.add(inputPanel);
        controlPanel.add(buttonPanel);

        return controlPanel;
    }

    class InsertButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            /* You will have to implement this */
            if (lastNameField.getText().equals("") ||
                    firstNameField.getText().equals("") ||
                    ageField.getText().equals("") ||
                    cityField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "All Fields must be filled");
                return;
            }
            try {
                PreparedStatement insertData = conn.prepareStatement("Insert Into People (last,First,age,city) " +
                        "Values (?,?,?,?)");
                insertData.setString(1, lastNameField.getText());
                insertData.setString(2, firstNameField.getText());
                insertData.setString(3, ageField.getText());
                insertData.setString(4, cityField.getText());
                insertData.execute();
            } catch (SQLException e) {
                System.err.println("Connection error: " + e);
                System.exit(1);
            }
            lastNameField.setText("");
            firstNameField.setText("");
            ageField.setText("");
            cityField.setText("");
        }
    }

    class QueryButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            /* as far as the columns, it is totally acceptable to
             * get all of the column data ahead of time, so you only
             * have to do it once, and just reprint the string
             * in the text area.
             */

            /* you want to change things here so that if the text of the
             * last name query field is empty, it should query for all rows.
             *
             * For now, if the last name query field is blank, it will execute:
             * SELECT * FROM People WHERE Last=''
             * which will give no results
             */
            try {
                textQueryArea.setText("");
                PreparedStatement stmt = queryStmtLastName;
                String lastNameText = lastNameQuery.getText();
                ResultSet rset;
                if (!lastNameText.equals("")) {
                    stmt.setString(1, lastNameText);
                    rset = stmt.executeQuery();
                } else {
                    rset = conn.prepareStatement("Select * from People").executeQuery();
                }
                ResultSetMetaData rsmd = rset.getMetaData();
                int numColumns = rsmd.getColumnCount();
                System.out.println("numcolumns is " + numColumns);

                String rowString = "First\tlast\tage\tcity\tid\n";
                while (rset.next()) {
                    for (int i = 1; i <= numColumns; i++) {
                        Object o = rset.getObject(i);
                        rowString += o.toString() + "\t";
                    }
                    rowString += "\n";
                }
                System.out.print("rowString  is\n" + rowString);
                textQueryArea.setText(rowString);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JDBCInterface();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
