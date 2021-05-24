package usermenu;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import menu.login.Login;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserMenu {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserMenu window = new UserMenu();
                    window.frame.setVisible(true);
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
        frame = new JFrame("Admin Functions");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton viewButton = new JButton("View Books");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        JButton rentBookButton = new JButton("Rent Book");
        JButton returnBookButton = new JButton("Return Book");
        JButton rentedBooksButton = new JButton("View Rented Books");

        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        frame.getContentPane().add(viewButton);
        frame.getContentPane().add(rentBookButton);
        frame.getContentPane().add(returnBookButton);
        frame.getContentPane().add(rentedBooksButton);


        JButton logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Login.main(null);
            }
        });
        frame.getContentPane().add(logOutButton);
        frame.setSize(600, 200);
        frame.setVisible(true);// making the frame visible
        frame.setLocationRelativeTo(null);
    }

}