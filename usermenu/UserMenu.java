package usermenu;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import menu.Login;
import person.NormalUser;
import utilities.ViewBook;

import java.awt.event.ActionListener;
import java.io.Reader;
import java.awt.event.ActionEvent;

public class UserMenu {

    private JFrame frmUserFunctions;
    protected static NormalUser user;
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
        frmUserFunctions = new JFrame("Admin Functions");
        frmUserFunctions.setTitle("User Functions");
        frmUserFunctions.setBounds(100, 100, 450, 300);
        frmUserFunctions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton viewButton = new JButton("View Books");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewBook.main(null);
            }
        });
        JButton rentBookButton = new JButton("Rent Book");
        rentBookButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RentBook.main(null);
        	}
        });
        JButton returnBookButton = new JButton("Return Book");
        JButton rentedBooksButton = new JButton("View Rented Books");

        frmUserFunctions.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        frmUserFunctions.getContentPane().add(viewButton);
        frmUserFunctions.getContentPane().add(rentBookButton);
        frmUserFunctions.getContentPane().add(returnBookButton);
        frmUserFunctions.getContentPane().add(rentedBooksButton);


        JButton logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmUserFunctions.dispose();
                Login.main(null);
            }
        });
        frmUserFunctions.getContentPane().add(logOutButton);
        frmUserFunctions.setSize(600, 200);
        frmUserFunctions.setVisible(true);// making the frame visible
        frmUserFunctions.setLocationRelativeTo(null);
    }

}