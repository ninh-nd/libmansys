package menu.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Reader;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

import menu.DatabaseManagement;
import person.*;

import java.sql.*;

public class Login {
	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton exitButton;
	private JLabel loginMenuLabel;
	private static DatabaseManagement db = new DatabaseManagement();

	/**
	 * Check username and password from database
	 */
	private void checkLogin(String username, String password) {
		String sql = "SELECT * from users WHERE username= ? and password= ? ";
		try {
			Connection conn = db.connect();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (username != null && password != null) {
				if (rs.next()) {
					JOptionPane.showMessageDialog(null, "Login Successfully");
					if (username == "admin") {
						// Redirect to administration window

					} else {
						// Redirect to according user's window
					}
				} else {
					JOptionPane.showMessageDialog(null, "Wrong username/password", "Login Error",
							JOptionPane.ERROR_MESSAGE);

				}
			}
		} catch (SQLException err) {
			System.out.println(err.getMessage());
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.db.connect();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);

		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(44, 92, 76, 14);
		frmLogin.getContentPane().add(userLabel);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(44, 155, 76, 14);
		frmLogin.getContentPane().add(passwordLabel);

		textField = new JTextField();
		textField.setBounds(130, 89, 227, 20);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(130, 152, 227, 20);
		frmLogin.getContentPane().add(passwordField);

		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = passwordField.getText();
				checkLogin(username, password);
			}
		});
		loginButton.setBounds(81, 197, 89, 23);
		frmLogin.getContentPane().add(loginButton);

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(268, 197, 89, 23);
		frmLogin.getContentPane().add(exitButton);

		loginMenuLabel = new JLabel("Library Management System");
		loginMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginMenuLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		loginMenuLabel.setBounds(50, 23, 324, 50);
		frmLogin.getContentPane().add(loginMenuLabel);
	}
}
