 	package menu;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import adminmenu.AdminMenu;
import person.Librarian;
import person.NormalUser;
import usermenu.UserMenu;

public class Login {
	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel loginMenuLabel;
	/**
	 * Check username and password from database
	 */
	private boolean checkLogin(String username, String password) {
		String sql = "SELECT * from users WHERE username= ? and password= ? ";
		try {
			Connection conn = DatabaseManagement.connect();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (username != null && password != null) {
				if (rs.next()) {
					JOptionPane.showMessageDialog(null, "Login Successfully");
					if (username.equals("admin")) { //Redirect to admin menu
						String getUserName = rs.getString(1);
						String getPassword = rs.getString(2);
						String getName = rs.getString(3);
						String getEmail = rs.getString(4);
						String getAddress = rs.getString(5);
						String getPhoneNumber = rs.getString(6);
						Librarian user = new Librarian(getUserName, getPassword, getName, getEmail, getAddress, getPhoneNumber);
						AdminMenu.setUser(user);
						AdminMenu.main(null);
						frmLogin.dispose();
					}
					else { //Redirect to user menu
						String getUserName = rs.getString(1);
						String getPassword = rs.getString(2);
						String getName = rs.getString(3);
						String getEmail = rs.getString(4);
						String getAddress = rs.getString(5);
						String getPhoneNumber = rs.getString(6);
						NormalUser user = new NormalUser(getUserName, getPassword, getName, getEmail, getAddress, getPhoneNumber);
						UserMenu.setUser(user);
						UserMenu.main(null);
						frmLogin.dispose();
					}
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "Wrong username/password", "Login Error",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		} catch (SQLException err) {
			System.out.println(err.getMessage());
		}
		return false;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
		passwordLabel.setBounds(44, 132, 76, 14);
		frmLogin.getContentPane().add(passwordLabel);

		textField = new JTextField();
		textField.setBounds(130, 89, 227, 20);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(130, 129, 227, 20);
		frmLogin.getContentPane().add(passwordField);

		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = passwordField.getText();
				if (checkLogin(username, password))
					frmLogin.dispose();
			}
		});
		loginButton.setBounds(172, 160, 89, 23);
		frmLogin.getContentPane().add(loginButton);

		loginMenuLabel = new JLabel("Library Management System");
		loginMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginMenuLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		loginMenuLabel.setBounds(50, 23, 324, 50);
		frmLogin.getContentPane().add(loginMenuLabel);
		frmLogin.getRootPane().setDefaultButton(loginButton); //Set Enter button on keyboard to login
		
		JLabel lblNewLabel = new JLabel("New to the library?");
		lblNewLabel.setBounds(44, 201, 115, 14);
		frmLogin.getContentPane().add(lblNewLabel);
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register.main(null);
			}
		});
		registerButton.setBounds(172, 197, 89, 23);
		frmLogin.getContentPane().add(registerButton);
	}
}
