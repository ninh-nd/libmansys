package menu.register;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import menu.DatabaseManagement;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JLayeredPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.Cursor;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Register {

	private JFrame frame;
	private JTextField usernameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField confirmpasswordField;
	private JButton confirmButton;
	private DatabaseManagement db = new DatabaseManagement();
	private JLayeredPane confirmPane;
	private JLayeredPane formPane;

	/**
	 * Check for duplicate username
	 */
	private boolean duplicateUser(String username) {
		if (!username.trim().isEmpty()) {
			try (Connection conn = db.connect();) {
				String sql = "SELECT username FROM users WHERE (username = ?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					JOptionPane.showMessageDialog(null, "Duplicate username", null, JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} catch (SQLException err) {
				System.out.println(err.getMessage());
			}
			return true;
		} else
			return false;
	}

	/**
	 * Check confirm password
	 */
	private boolean confirmPassword(String password, String confirmPassword) {
		if (!password.isEmpty() && !confirmPassword.isEmpty()) {
			if (!password.equals(confirmPassword)) {
				JOptionPane.showMessageDialog(null, "Password does not match", null, JOptionPane.ERROR_MESSAGE);
				return false;
			}
			return true;
		} else
			return false;
	}

	/**
	 * Check duplicate email
	 */
	private boolean duplicateEmail(String email) {
		if (!email.trim().isEmpty()) {
			try (Connection conn = db.connect();) {
				String sql = "SELECT email FROM users WHERE (email = ?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, email);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					JOptionPane.showMessageDialog(null, "Duplicate email", null, JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} catch (SQLException err) {
				System.out.println(err.getMessage());
			}
			return true;
		} else
			return false;
	}

	private void registration(String username, String email, String password, String confirmPassword) {
		if (username.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()
				|| confirmPassword.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Blank field", null, JOptionPane.ERROR_MESSAGE);
		} else {
			if (duplicateUser(username) && duplicateEmail(email) && confirmPassword(password, confirmPassword)) {
				try {
					String sql = "INSERT INTO users (username,email,password) VALUES (?,?,?)";
					Connection conn = db.connect();
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, username);
					stmt.setString(2, email);
					stmt.setString(3, password);
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Register successfully", null, JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException err) {
					System.out.println(err.getMessage());
				}
			}
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
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
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.setBounds(100, 100, 451, 556);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		confirmPane = new JLayeredPane();
		confirmPane.setBackground(Color.WHITE);
		confirmPane.setBounds(10, 376, 415, 130);
		frame.getContentPane().add(confirmPane);
		confirmPane.setLayout(new BorderLayout(0, 0));

		confirmButton = new JButton("Confirm");
		confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		confirmButton.setMargin(new Insets(0, 0, 0, 0));
		confirmButton.setBorderPainted(false);
		confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		confirmButton.setBorder(new LineBorder(Color.GREEN, 1, true));
		confirmButton.setBackground(new Color(153, 255, 102));
		confirmButton.setForeground(Color.BLACK);
		confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		confirmPane.add(confirmButton, BorderLayout.CENTER);
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registration(usernameField.getText(), emailField.getText(), passwordField.getText(),
						confirmpasswordField.getText());
			}
		});

		formPane = new JLayeredPane();
		formPane.setBounds(10, 11, 415, 354);
		frame.getContentPane().add(formPane);
		formPane.setLayout(null);

		JLabel usernameTitle = new JLabel("Username");
		usernameTitle.setBounds(31, 58, 104, 16);
		formPane.add(usernameTitle);
		usernameTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		usernameTitle.setHorizontalAlignment(SwingConstants.CENTER);

		usernameField = new JTextField();
		usernameField.setBounds(166, 56, 224, 20);
		formPane.add(usernameField);
		usernameField.setColumns(10);

		JLabel emailTitle = new JLabel("Email");
		emailTitle.setBounds(31, 134, 104, 16);
		formPane.add(emailTitle);
		emailTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailTitle.setHorizontalAlignment(SwingConstants.CENTER);

		emailField = new JTextField();
		emailField.setBounds(166, 132, 224, 20);
		formPane.add(emailField);
		emailField.setColumns(10);

		JLabel passwordTitle = new JLabel("Password");
		passwordTitle.setBounds(31, 210, 104, 16);
		formPane.add(passwordTitle);
		passwordTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordTitle.setHorizontalAlignment(SwingConstants.CENTER);

		passwordField = new JPasswordField();
		passwordField.setBounds(166, 208, 224, 20);
		formPane.add(passwordField);

		JLabel confirmpasswordTitle = new JLabel("Confirm password");
		confirmpasswordTitle.setBounds(31, 286, 104, 16);
		formPane.add(confirmpasswordTitle);
		confirmpasswordTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		confirmpasswordTitle.setHorizontalAlignment(SwingConstants.CENTER);

		confirmpasswordField = new JPasswordField();
		confirmpasswordField.setBounds(166, 284, 224, 20);
		formPane.add(confirmpasswordField);
	}
}
