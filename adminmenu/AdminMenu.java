package adminmenu;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import menu.Login;
import net.proteanit.sql.DbUtils;
import person.NormalUser;
import utilities.ViewBook;
import utilities.ViewHistory;
import utilities.ViewRentedBook;

public class AdminMenu {

    private JFrame frmAdminFunctions;
    protected static NormalUser user;
    private JTable currentTable;
    private JTable historyTable;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminMenu window = new AdminMenu();
                    window.frmAdminFunctions.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public AdminMenu() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmAdminFunctions = new JFrame("User Functions");
        frmAdminFunctions.setTitle("Admin Functions");
        frmAdminFunctions.setBounds(100, 100, 450, 300);
        frmAdminFunctions.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 485, 221);
        frmAdminFunctions.getContentPane().setLayout(null);
        frmAdminFunctions.getContentPane().add(panel);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 48, 485, 173);
        panel.add(scrollPane);

        currentTable = new JTable();
        scrollPane.setViewportView(currentTable);
        currentTable.setModel(DbUtils.resultSetToTableModel(ViewRentedBook.viewRentedBook()));
        JLabel lblNewLabel = new JLabel("Current books borrowed:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(10, 11, 165, 26);
        panel.add(lblNewLabel);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(386, 11, 89, 23);
        panel.add(refreshButton);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 262, 485, 221);
        frmAdminFunctions.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 48, 485, 173);
        panel_1.add(scrollPane_1);

        historyTable = new JTable();
        scrollPane_1.setViewportView(historyTable);
        historyTable.setModel(DbUtils.resultSetToTableModel(ViewHistory.viewHistory()));
        JLabel lblBookBorrowingsHistory = new JLabel("Book borrowing's history:");
        lblBookBorrowingsHistory.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblBookBorrowingsHistory.setBounds(10, 11, 165, 26);
        panel_1.add(lblBookBorrowingsHistory);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(485, 0, 280, 481);
        frmAdminFunctions.getContentPane().add(panel_2);
        panel_2.setLayout(null);
        frmAdminFunctions.setSize(781, 544);
        frmAdminFunctions.setVisible(true);// making the frame visible
        frmAdminFunctions.setLocationRelativeTo(null);

        JButton addBookButton = new JButton("Add Book");
        addBookButton.setBounds(151, 317, 119, 37);
        panel_2.add(addBookButton);
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmAdminFunctions.setState(Frame.ICONIFIED);
                AddBook.main(null);
            }
        });
        JButton logOutButton = new JButton("Log Out");
        logOutButton.setBounds(151, 365, 119, 37);
        panel_2.add(logOutButton);
        logOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmAdminFunctions.dispose();
                Login.main(null);
            }
        });
        JButton addCategoryButton = new JButton("Add category");
        panel_2.add(addCategoryButton);
        addCategoryButton.setBounds(23, 365, 119, 37);
        addCategoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmAdminFunctions.setState(Frame.ICONIFIED);
                AddCategory.main(null);
            }
        });
        JButton viewButton = new JButton("View Books");
        panel_2.add(viewButton);
        viewButton.setBounds(23, 317, 119, 37);
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmAdminFunctions.setState(Frame.ICONIFIED);
                ViewBook.main(null);
            }
        });
    }
}