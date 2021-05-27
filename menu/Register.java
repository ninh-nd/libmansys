package menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JLayeredPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Cursor;

public class Register {

	private JFrame frmRegister;
	private JTextField usernameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField confirmpasswordField;
	private JButton confirmButton;
	private JLayeredPane confirmPane;
	private JLayeredPane formPane;
	private JTextField addressField;
	private JTextField phoneField;
	private JLabel phoneLabel;
	private JLabel nameLabel;
	private JTextField nameField;

	/**
	 * Check for duplicate username
	 */
	private boolean duplicateUser(String username) {
		if (!username.trim().isEmpty()) {
			try (Connection conn = DatabaseManagement.connect();) {
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
			try (Connection conn = DatabaseManagement.connect();) {
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

	private void registration(String username, String name, String email, String address, String phone, String password, String confirmPassword) {
		if (username.trim().isEmpty() || email.trim().isEmpty() || address.trim().isEmpty() || phone.trim().isEmpty() || password.trim().isEmpty()
				|| confirmPassword.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Blank field", null, JOptionPane.ERROR_MESSAGE);
		} else {
			if (duplicateUser(username) && duplicateEmail(email) && confirmPassword(password, confirmPassword)) {
				try {
					String sql = "INSERT INTO users (username,password,name,email,address,phone) VALUES (?,?,?,?,?,?)";
					Connection conn = DatabaseManagement.connect();
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, username);
					stmt.setString(2, password);
					stmt.setString(3, name);
					stmt.setString(4, email);
					stmt.setString(5, address);
					stmt.setString(6, phone);
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Register successfully", null, JOptionPane.PLAIN_MESSAGE);
					frmRegister.dispose();
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
					window.frmRegister.setVisible(true);
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
		frmRegister = new JFrame();
		frmRegister.setTitle("Register");
		frmRegister.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		frmRegister.setBounds(100, 100, 451, 556);
		frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegister.getContentPane().setLayout(null);

		confirmPane = new JLayeredPane();
		confirmPane.setBackground(Color.WHITE);
		confirmPane.setBounds(10, 450, 415, 56);
		frmRegister.getContentPane().add(confirmPane);
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
				registration(usernameField.getText(), nameField.getText() ,emailField.getText(), addressField.getText(), phoneField.getText(), passwordField.getText(),
						confirmpasswordField.getText());
			}
		});

		formPane = new JLayeredPane();
		formPane.setBounds(10, 11, 415, 440);
		frmRegister.getContentPane().add(formPane);
		formPane.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("31px"),
				ColumnSpec.decode("104px:grow"),
				ColumnSpec.decode("31px"),
				ColumnSpec.decode("224px:grow"),},
			new RowSpec[] {
				RowSpec.decode("max(30dlu;min)"),
				RowSpec.decode("20px"),
				RowSpec.decode("max(30px;default)"),
				RowSpec.decode("20px"),
				RowSpec.decode("max(30px;min)"),
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("max(30px;default)"),
				RowSpec.decode("20px"),
				RowSpec.decode("max(30px;min)"),
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("max(30px;min)"),
				RowSpec.decode("20px"),
				RowSpec.decode("max(30px;min)"),
				RowSpec.decode("20px"),}));

		JLabel usernameTitle = new JLabel("Username");
		formPane.add(usernameTitle, "2, 2, fill, center");
		usernameTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		usernameTitle.setHorizontalAlignment(SwingConstants.CENTER);

		usernameField = new JTextField();
		formPane.add(usernameField, "4, 2, fill, top");
		usernameField.setColumns(10);

		JLabel emailTitle = new JLabel("Email");
		formPane.add(emailTitle, "2, 4, fill, center");
		emailTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailTitle.setHorizontalAlignment(SwingConstants.CENTER);

		emailField = new JTextField();
		formPane.add(emailField, "4, 4, fill, top");
		emailField.setColumns(10);
		
		nameLabel = new JLabel("Name");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		formPane.add(nameLabel, "2, 6");
		
		nameField = new JTextField();
		formPane.add(nameField, "4, 6, fill, default");
		nameField.setColumns(10);
		
		addressField = new JTextField();
		formPane.add(addressField, "4, 8, fill, default");
		addressField.setColumns(10);
		
		phoneLabel = new JLabel("Phone number");
		phoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		formPane.add(phoneLabel, "2, 10");
		
		phoneField = new JTextField();
		phoneField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		formPane.add(phoneField, "4, 10, fill, default");
		phoneField.setColumns(10);

		JLabel passwordTitle = new JLabel("Password");
		formPane.add(passwordTitle, "2, 12, fill, center");
		passwordTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordTitle.setHorizontalAlignment(SwingConstants.CENTER);

		passwordField = new JPasswordField();
		formPane.add(passwordField, "4, 12, fill, top");

		JLabel confirmpasswordTitle = new JLabel("Confirm password");
		formPane.add(confirmpasswordTitle, "2, 14, center, center");
		confirmpasswordTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		confirmpasswordTitle.setHorizontalAlignment(SwingConstants.CENTER);

		confirmpasswordField = new JPasswordField();
		formPane.add(confirmpasswordField, "4, 14, fill, top");
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		formPane.add(addressLabel, "2, 8, center, top");
	}
}
