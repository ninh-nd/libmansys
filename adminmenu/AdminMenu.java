package adminmenu;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import utilities.ViewBook;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu window = new AdminMenu();
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
	public AdminMenu() {
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
				new ViewBook();
			}
		});
		JButton usersButton = new JButton("View Users");
		JButton rentedBooksButton = new JButton("View Rented Books");
		JButton addBookButton = new JButton("Add Book");
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frame.getContentPane().add(viewButton);
		frame.getContentPane().add(usersButton);
		frame.getContentPane().add(rentedBooksButton);
		frame.getContentPane().add(addBookButton);
		frame.setSize(600, 200);
		frame.setVisible(true);// making the frame visible
		frame.setLocationRelativeTo(null);
	}

}
