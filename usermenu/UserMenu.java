package usermenu;

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
import javax.swing.JTextArea;

import net.proteanit.sql.DbUtils;
import person.NormalUser;
import utilities.ViewBook;
import utilities.ViewHistory;
import utilities.ViewRentedBook;

import menu.Login;

public class UserMenu {

    protected static JFrame frmUserFunctions;
    protected static NormalUser user;
    private JTable currentTable;
    private JTable historyTable;

    public static void setUser(NormalUser user) {
        UserMenu.user = user;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserMenu window = new UserMenu();
                    window.frmUserFunctions.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public UserMenu() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmUserFunctions = new JFrame("User Functions");
        frmUserFunctions.setBounds(100, 100, 450, 300);
        frmUserFunctions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 485, 221);
        frmUserFunctions.getContentPane().setLayout(null);
        frmUserFunctions.getContentPane().add(panel);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 48, 485, 173);
        panel.add(scrollPane);

        currentTable = new JTable();
        scrollPane.setViewportView(currentTable);
        currentTable.setModel(DbUtils.resultSetToTableModel(ViewRentedBook.viewUserRentedBook(user)));
        JLabel lblNewLabel = new JLabel("Current books borrowed:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(10, 11, 165, 26);
        panel.add(lblNewLabel);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentTable.setModel(DbUtils.resultSetToTableModel(ViewRentedBook.viewUserRentedBook(user)));
                historyTable.setModel(DbUtils.resultSetToTableModel(ViewHistory.viewUserHistory(user)));
            }
        });
        refreshButton.setBounds(386, 11, 89, 23);
        panel.add(refreshButton);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 262, 485, 221);
        frmUserFunctions.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 48, 485, 173);
        panel_1.add(scrollPane_1);

        historyTable = new JTable();
        scrollPane_1.setViewportView(historyTable);
        historyTable.setModel(DbUtils.resultSetToTableModel(ViewHistory.viewUserHistory(user)));
        JLabel lblBookBorrowingsHistory = new JLabel("Book borrowing's history:");
        lblBookBorrowingsHistory.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblBookBorrowingsHistory.setBounds(10, 11, 165, 26);
        panel_1.add(lblBookBorrowingsHistory);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(485, 0, 280, 481);
        frmUserFunctions.getContentPane().add(panel_2);
        panel_2.setLayout(null);
        JButton renewButton = new JButton("Renew Books");
        renewButton.setBounds(151, 365, 119, 37);
        panel_2.add(renewButton);
        JButton viewButton = new JButton("View Books");
        viewButton.setBounds(23, 365, 119, 37);
        panel_2.add(viewButton);
        JButton rentBooksButton = new JButton("Rent Books");
        rentBooksButton.setBounds(23, 317, 119, 37);
        panel_2.add(rentBooksButton);
        JButton returnBooksButton = new JButton("Return Books");
        returnBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frmUserFunctions.setState(Frame.ICONIFIED);
                ReturnBook.main(null);
            }
        });
        returnBooksButton.setBounds(151, 317, 119, 37);
        panel_2.add(returnBooksButton);

        JButton logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmUserFunctions.dispose();
				Login.main(null);
            }
        });
        logOutButton.setBounds(151, 414, 119, 37);
        panel_2.add(logOutButton);
        JLabel lblNewLabel_1 = new JLabel("User's information");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNewLabel_1.setBounds(69, 11, 142, 35);
        panel_2.add(lblNewLabel_1);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(10, 42, 260, 151);
        panel_2.add(textArea);
        
        JButton changePwdButton = new JButton("Change \nPasword");
        changePwdButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
        changePwdButton.setBounds(23, 414, 119, 37);
        panel_2.add(changePwdButton);
        
        textArea.append("- Username: " + user.getUsername() + "\n");
        textArea.append("- Name: " + user.getName() + "\n");
        textArea.append("- Email: " + user.getEmail() + "\n");
        textArea.append("- Phone: " + user.getPhoneNumber() + "\n");
        textArea.append("- Address: " + user.getAddress() + "\n");
        rentBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frmUserFunctions.setState(Frame.ICONIFIED);
                RentBook.main(null);
            }
        });
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frmUserFunctions.setState(Frame.ICONIFIED);
                ViewBook.main(null);
            }
        });
        renewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frmUserFunctions.setState(Frame.ICONIFIED);
                RenewBook.main(null);
            }
        });
        changePwdButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frmUserFunctions.setState(Frame.ICONIFIED);
				ChangeUserPassword.main(null);
				
			}
		});
        frmUserFunctions.setSize(781, 544);
        frmUserFunctions.setVisible(true);// making the frame visible
        frmUserFunctions.setLocationRelativeTo(null);
    }
}