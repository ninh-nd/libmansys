package utilities;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import adminmenu.AdminMenu;
import menu.DatabaseManagement;
import net.proteanit.sql.DbUtils;
import usermenu.UserMenu;

public class ViewBook {

    private JFrame frmBookList;
    private JTable table = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    private JTextField searchField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewBook window = new ViewBook();
                    window.frmBookList.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public ViewBook() {
        String sql = "SELECT * FROM books ORDER BY book_id";
        try {
            Connection conn = DatabaseManagement.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmBookList = new JFrame();
        frmBookList.setTitle("Book list");
        frmBookList.setVisible(true);
        frmBookList.setBounds(100, 100, 788, 377);
        frmBookList.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmBookList.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 145, 772, 193);
        frmBookList.getContentPane().add(scrollPane);
        scrollPane.setViewportView(table);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 772, 144);
        frmBookList.getContentPane().add(panel);
        panel.setLayout(null);

        searchField = new JTextField();
        searchField.setBounds(276, 47, 219, 20);
        panel.add(searchField);
        searchField.setColumns(10);

        JPanel optionPanel = new JPanel();
        optionPanel.setBounds(0, 5, 772, 36);
        panel.add(optionPanel);
        optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JRadioButton searchTitle = new JRadioButton("Search by title");
        optionPanel.add(searchTitle);

        JRadioButton searchCategory = new JRadioButton("Search by category");
        optionPanel.add(searchCategory);

        JRadioButton searchAuthor = new JRadioButton("Search by author");
        optionPanel.add(searchAuthor);

        JRadioButton searchPublisher = new JRadioButton("Search by publisher");
        optionPanel.add(searchPublisher);

        ButtonGroup group = new ButtonGroup(); // Add buttons into a group so that one can only choose one radiobox
        group.add(searchTitle);
        group.add(searchPublisher);
        group.add(searchCategory);
        group.add(searchAuthor);

        JButton searchButton = new JButton("Search");
        frmBookList.getRootPane().setDefaultButton(searchButton); // Pressing Enter will also search
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (searchCategory.isSelected() && !searchField.getText().isBlank())
                    table.setModel(DbUtils.resultSetToTableModel(Search.searchCategory(searchField.getText())));

                if (searchTitle.isSelected() && !searchField.getText().isBlank())
                    table.setModel(DbUtils.resultSetToTableModel(Search.searchTitle(searchField.getText())));

                if (searchPublisher.isSelected() && !searchField.getText().isBlank())
                    table.setModel(DbUtils.resultSetToTableModel(Search.searchPublisher(searchField.getText())));

                if (searchAuthor.isSelected() && !searchField.getText().isBlank())
                    table.setModel(DbUtils.resultSetToTableModel(Search.searchAuthor(searchField.getText())));

                if (searchField.getText().isBlank())
                    table.setModel(DbUtils.resultSetToTableModel(Search.showAllBook())); // Return list of all book if
                // the search field is empty
            }
        });
        searchButton.setBounds(341, 78, 89, 20);
        panel.add(searchButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(25, 15, 70, 23);
//        backButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//                }
//            }
//        });
        panel.add(backButton);
    }
    public static ResultSet viewBookAvailable() {
        String sql = "Select book_id, title, author, category, publisher, book_status FROM books WHERE book_status = 'Available' ORDER BY book_id";
        try {
            Connection conn = DatabaseManagement.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            return rs;
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        return null;
    }
}