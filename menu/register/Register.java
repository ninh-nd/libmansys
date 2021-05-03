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

public class Register {

	private JFrame frame;
	private JTextField usernameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField confirmpasswordField;
	private JButton confirmButton;
	private DatabaseManagement db = new DatabaseManagement();
    /**
     * Check for duplicate username
     */
	private boolean duplicateUser(String username) {
		try (Connection conn = db.connect();) {
			if (username != null) {
				String sql = "SELECT username FROM users WHERE (username = ?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					JOptionPane.showMessageDialog(null, "Duplicate username", null, JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		} catch (SQLException err) {
			System.out.println(err.getMessage());
		}
		return true;
	}
    /**
     * Check confirm password
     */
    private boolean confirmPassword(String password, String confirmPassword) {
            if (password != null && confirmPassword != null) {
                /**
                 * Check confirmed password
                 */
                if (!password.equals(confirmPassword)) {
                	JOptionPane.showMessageDialog(null, "Password does not match", null, JOptionPane.ERROR_MESSAGE);
                	return false;
                }
                }
            return true;
    }
    /**
     * Check duplicate email
     */
	private boolean duplicateEmail(String email) {
		try (Connection conn = db.connect();) {
			if (email != null) {
				String sql = "SELECT email FROM users WHERE (email = ?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, email);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					JOptionPane.showMessageDialog(null, "Duplicate email", null, JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		} catch (SQLException err) {
			System.out.println(err.getMessage());
		}
		return true;
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
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{149, 221, 0};
		gridBagLayout.rowHeights = new int[]{155, 30, 30, 30, 30, 30, 30, 30, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel usernameTitle = new JLabel("Username");
		usernameTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		usernameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_usernameTitle = new GridBagConstraints();
		gbc_usernameTitle.insets = new Insets(0, 0, 5, 5);
		gbc_usernameTitle.gridx = 0;
		gbc_usernameTitle.gridy = 1;
		frame.getContentPane().add(usernameTitle, gbc_usernameTitle);
		
		usernameField = new JTextField();
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.fill = GridBagConstraints.BOTH;
		gbc_usernameField.insets = new Insets(0, 0, 5, 0);
		gbc_usernameField.gridx = 1;
		gbc_usernameField.gridy = 1;
		frame.getContentPane().add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);
		
		JLabel emailTitle = new JLabel("Email");
		emailTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_emailTitle = new GridBagConstraints();
		gbc_emailTitle.insets = new Insets(0, 0, 5, 5);
		gbc_emailTitle.gridx = 0;
		gbc_emailTitle.gridy = 3;
		frame.getContentPane().add(emailTitle, gbc_emailTitle);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.fill = GridBagConstraints.BOTH;
		gbc_emailField.insets = new Insets(0, 0, 5, 0);
		gbc_emailField.gridx = 1;
		gbc_emailField.gridy = 3;
		frame.getContentPane().add(emailField, gbc_emailField);
		
		JLabel passwordTitle = new JLabel("Password");
		passwordTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_passwordTitle = new GridBagConstraints();
		gbc_passwordTitle.insets = new Insets(0, 0, 5, 5);
		gbc_passwordTitle.gridx = 0;
		gbc_passwordTitle.gridy = 5;
		frame.getContentPane().add(passwordTitle, gbc_passwordTitle);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 5;
		frame.getContentPane().add(passwordField, gbc_passwordField);
		
		JLabel confirmpasswordTitle = new JLabel("Confirm password");
		confirmpasswordTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		confirmpasswordTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_confirmpasswordTitle = new GridBagConstraints();
		gbc_confirmpasswordTitle.insets = new Insets(0, 0, 5, 5);
		gbc_confirmpasswordTitle.gridx = 0;
		gbc_confirmpasswordTitle.gridy = 7;
		frame.getContentPane().add(confirmpasswordTitle, gbc_confirmpasswordTitle);
		
		confirmpasswordField = new JPasswordField();
		GridBagConstraints gbc_confirmpasswordField = new GridBagConstraints();
		gbc_confirmpasswordField.insets = new Insets(0, 0, 5, 0);
		gbc_confirmpasswordField.fill = GridBagConstraints.BOTH;
		gbc_confirmpasswordField.gridx = 1;
		gbc_confirmpasswordField.gridy = 7;
		frame.getContentPane().add(confirmpasswordField, gbc_confirmpasswordField);
		
		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		GridBagConstraints gbc_confirmButton = new GridBagConstraints();
		gbc_confirmButton.gridx = 1;
		gbc_confirmButton.gridy = 9;
		frame.getContentPane().add(confirmButton, gbc_confirmButton);
	}
}
