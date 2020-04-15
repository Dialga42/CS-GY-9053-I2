package Question2JavaSwing;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordFinder extends JFrame {

    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;

    JFileChooser jFileChooser;
    private JPanel topPanel; // the top line of objects is going to go here
    JLabel label;
    JTextField textField;
    JButton button;
    JTextArea textArea;
    WordList wordList;

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 400;

    private String pre = "";

    public WordFinder() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set the size correctly
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        jFileChooser = new JFileChooser(".");
        wordList = new WordList();
        topPanel = new JPanel();
        createMenus();

        // there should be objects in the top panel
        label = new JLabel("Find:");
        topPanel.add(label);

        textField = new JTextField(10);
        textField.addCaretListener(new SearchListener());
        topPanel.add(textField);

        button = new JButton("Clear");
        button.addActionListener((e) -> textField.setText(""));
        topPanel.add(button);

        final int AREA_ROWS = 10;
        final int AREA_COLUMNS = 20;
        textArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        textArea.setEditable(false);

        // There should probably be something passed into the JScrollPane
        JScrollPane listScroller = new JScrollPane(textArea);
        listScroller.setPreferredSize(new Dimension(250, 80));
        // and of course you will want them to be properly aligned in the frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(listScroller);
    }

    private void createMenus() {

        menuBar = new JMenuBar();

        /* add a "File" menu with:
         * "Open" item which allows you to choose a new file
         * "Exit" item which ends the process with System.exit(0);
         * Key shortcuts are optional
         */

        menu = new JMenu("File");
        menu.add(createFileOpenItem());
        menu.add(createFileExitItem());
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

    }

    /**
     * Creates the File->Exit menu item and sets its action listener.
     *
     * @return the menu item
     */
    public JMenuItem createFileExitItem() {
        JMenuItem item = new JMenuItem("Exit");
        item.addActionListener((e) -> System.exit(0));
        return item;
    }

    public JMenuItem createFileOpenItem() {
        JMenuItem item = new JMenuItem("Open");
        item.addActionListener(new OpenFileListener());
        return item;
    }

    class SearchListener implements CaretListener {
        public void caretUpdate(CaretEvent e) {
            if (!pre.equals(textField.getText())) {
                pre = textField.getText();
                List searchResult = wordList.find(textField.getText()); // figure out from WordList how to get this
                textArea.setText("");
                for (Object s : searchResult) {
                    textArea.append(s.toString() + "\n");
                }
                textArea.setCaretPosition(0);
            }
        }
    }

    class OpenFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int returnVal = jFileChooser.showOpenDialog(getParent());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " + jFileChooser.getSelectedFile().getAbsolutePath());

                try {
                    InputStream in = new FileInputStream(jFileChooser.getSelectedFile().getAbsolutePath());
                    wordList.load(in);
                } catch (Exception ex) {
                }

                List searchResult = wordList.find(textField.getText()); // figure out from WordList how to get this
                textArea.setText("");
                for (Object s : searchResult) {
                    textArea.append(s.toString() + "\n");
                }
                textArea.setCaretPosition(0);
            }
        }
    }


    public static void main(String[] args) {
        WordFinder wordFinder = new WordFinder();
        wordFinder.setVisible(true);
    }
}
