package menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Register {

	private JFrame frmRegister;
	private JTextField usernameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField confirmpasswordField;
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
					System.out.println(err.getSQLState());
					if (err.getSQLState().equals("23514")) {
						JOptionPane.showMessageDialog(null, "Invalid phone/email format", null, JOptionPane.ERROR_MESSAGE);
					}
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
		frmRegister.setBounds(100, 100, 852, 556);
		frmRegister.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegister.getContentPane().setLayout(null);

		confirmPane = new JLayeredPane();
		confirmPane.setBackground(Color.WHITE);
		confirmPane.setBounds(210, 450, 415, 56);
		frmRegister.getContentPane().add(confirmPane);
		confirmPane.setLayout(null);

		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBounds(90, 0, 234, 56);
		confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		confirmButton.setMargin(new Insets(0, 0, 0, 0));
		confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		confirmButton.setBorder(new LineBorder(Color.BLACK, 1, true));
		confirmButton.setBackground(new Color(153, 255, 102));
		confirmButton.setForeground(Color.BLACK);
		confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		confirmPane.add(confirmButton);
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registration(usernameField.getText(), nameField.getText() ,emailField.getText(), addressField.getText(), phoneField.getText(), passwordField.getText(),
						confirmpasswordField.getText());
			}
		});

		formPane = new JLayeredPane();
		formPane.setBounds(10, 11, 816, 442);
		frmRegister.getContentPane().add(formPane);
				GridBagLayout gbl_formPane = new GridBagLayout();
				gbl_formPane.columnWidths = new int[]{204, 204, 204, 204, 0};
				gbl_formPane.rowHeights = new int[]{110, 110, 110, 110, 0};
				gbl_formPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				gbl_formPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				formPane.setLayout(gbl_formPane);
						
								JLabel usernameTitle = new JLabel("Username");
								GridBagConstraints gbc_usernameTitle = new GridBagConstraints();
								gbc_usernameTitle.fill = GridBagConstraints.BOTH;
								gbc_usernameTitle.insets = new Insets(0, 0, 5, 5);
								gbc_usernameTitle.gridx = 0;
								gbc_usernameTitle.gridy = 0;
								formPane.add(usernameTitle, gbc_usernameTitle);
								usernameTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
								usernameTitle.setHorizontalAlignment(SwingConstants.CENTER);
				
						usernameField = new JTextField();
						GridBagConstraints gbc_usernameField = new GridBagConstraints();
						gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
						gbc_usernameField.insets = new Insets(0, 0, 5, 5);
						gbc_usernameField.gridx = 1;
						gbc_usernameField.gridy = 0;
						formPane.add(usernameField, gbc_usernameField);
						usernameField.setColumns(10);
						
								JLabel emailTitle = new JLabel("Email");
								GridBagConstraints gbc_emailTitle = new GridBagConstraints();
								gbc_emailTitle.fill = GridBagConstraints.BOTH;
								gbc_emailTitle.insets = new Insets(0, 0, 5, 5);
								gbc_emailTitle.gridx = 2;
								gbc_emailTitle.gridy = 0;
								formPane.add(emailTitle, gbc_emailTitle);
								emailTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
								emailTitle.setHorizontalAlignment(SwingConstants.CENTER);
								
										emailField = new JTextField();
										GridBagConstraints gbc_emailField = new GridBagConstraints();
										gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
										gbc_emailField.insets = new Insets(0, 0, 5, 0);
										gbc_emailField.gridx = 3;
										gbc_emailField.gridy = 0;
										formPane.add(emailField, gbc_emailField);
										emailField.setColumns(10);
						
								JLabel passwordTitle = new JLabel("Password");
								GridBagConstraints gbc_passwordTitle = new GridBagConstraints();
								gbc_passwordTitle.fill = GridBagConstraints.BOTH;
								gbc_passwordTitle.insets = new Insets(0, 0, 5, 5);
								gbc_passwordTitle.gridx = 0;
								gbc_passwordTitle.gridy = 1;
								formPane.add(passwordTitle, gbc_passwordTitle);
								passwordTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
								passwordTitle.setHorizontalAlignment(SwingConstants.CENTER);
				
						passwordField = new JPasswordField();
						GridBagConstraints gbc_passwordField = new GridBagConstraints();
						gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
						gbc_passwordField.insets = new Insets(0, 0, 5, 5);
						gbc_passwordField.gridx = 1;
						gbc_passwordField.gridy = 1;
						formPane.add(passwordField, gbc_passwordField);
				
				nameLabel = new JLabel("Name");
				nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
				nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
				GridBagConstraints gbc_nameLabel = new GridBagConstraints();
				gbc_nameLabel.fill = GridBagConstraints.BOTH;
				gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
				gbc_nameLabel.gridx = 2;
				gbc_nameLabel.gridy = 1;
				formPane.add(nameLabel, gbc_nameLabel);
						
						nameField = new JTextField();
						GridBagConstraints gbc_nameField = new GridBagConstraints();
						gbc_nameField.insets = new Insets(0, 0, 5, 0);
						gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
						gbc_nameField.gridx = 3;
						gbc_nameField.gridy = 1;
						formPane.add(nameField, gbc_nameField);
						nameField.setColumns(10);
				
						JLabel confirmpasswordTitle = new JLabel("Confirm password");
						GridBagConstraints gbc_confirmpasswordTitle = new GridBagConstraints();
						gbc_confirmpasswordTitle.fill = GridBagConstraints.BOTH;
						gbc_confirmpasswordTitle.insets = new Insets(0, 0, 5, 5);
						gbc_confirmpasswordTitle.gridx = 0;
						gbc_confirmpasswordTitle.gridy = 2;
						formPane.add(confirmpasswordTitle, gbc_confirmpasswordTitle);
						confirmpasswordTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
						confirmpasswordTitle.setHorizontalAlignment(SwingConstants.CENTER);
				
						confirmpasswordField = new JPasswordField();
						GridBagConstraints gbc_confirmpasswordField = new GridBagConstraints();
						gbc_confirmpasswordField.insets = new Insets(0, 0, 5, 5);
						gbc_confirmpasswordField.fill = GridBagConstraints.HORIZONTAL;
						gbc_confirmpasswordField.gridx = 1;
						gbc_confirmpasswordField.gridy = 2;
						formPane.add(confirmpasswordField, gbc_confirmpasswordField);
				
				phoneLabel = new JLabel("Phone number");
				phoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
				phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
				GridBagConstraints gbc_phoneLabel = new GridBagConstraints();
				gbc_phoneLabel.fill = GridBagConstraints.BOTH;
				gbc_phoneLabel.insets = new Insets(0, 0, 5, 5);
				gbc_phoneLabel.gridx = 2;
				gbc_phoneLabel.gridy = 2;
				formPane.add(phoneLabel, gbc_phoneLabel);
				
				phoneField = new JTextField();
				phoneField.setFont(new Font("Tahoma", Font.PLAIN, 11));
				GridBagConstraints gbc_phoneField = new GridBagConstraints();
				gbc_phoneField.fill = GridBagConstraints.HORIZONTAL;
				gbc_phoneField.insets = new Insets(0, 0, 5, 0);
				gbc_phoneField.gridx = 3;
				gbc_phoneField.gridy = 2;
				formPane.add(phoneField, gbc_phoneField);
				phoneField.setColumns(10);
						
						JLabel addressLabel = new JLabel("Address");
						addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
						addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
						GridBagConstraints gbc_addressLabel = new GridBagConstraints();
						gbc_addressLabel.fill = GridBagConstraints.BOTH;
						gbc_addressLabel.insets = new Insets(0, 0, 0, 5);
						gbc_addressLabel.gridx = 2;
						gbc_addressLabel.gridy = 3;
						formPane.add(addressLabel, gbc_addressLabel);
						
						addressField = new JTextField();
						GridBagConstraints gbc_addressField = new GridBagConstraints();
						gbc_addressField.fill = GridBagConstraints.HORIZONTAL;
						gbc_addressField.gridx = 3;
						gbc_addressField.gridy = 3;
						formPane.add(addressField, gbc_addressField);
						addressField.setColumns(10);
	}
}
