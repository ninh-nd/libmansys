package usermenu;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import menu.Login;
import person.NormalUser;
import javax.swing.SwingConstants;

public class ChangeUserPassword extends JFrame {

	private JPanel contentPane;
	private JLabel New_passwordLabel;
	private JLabel Confirm_passwordLabel;
	private JPasswordField Old_passwordField;
	private JPasswordField New_passwordField;
	private JPasswordField Confirm_passwordField;
	private JButton backButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeUserPassword frame = new ChangeUserPassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChangeUserPassword() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 417, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 22, 403, 193);
		contentPane.add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
        BasicArrowButton backButton = new BasicArrowButton(BasicArrowButton.WEST);
        backButton.setBounds(0, 0, 62, 21);
		contentPane.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserMenu.main(null);
			}
		});
		
		JLabel Old_passwordLabel = new JLabel("Old password");
		Old_passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(Old_passwordLabel, "4, 4");
		
		Old_passwordField = new JPasswordField();
		panel.add(Old_passwordField, "8, 4, fill, default");
		
		New_passwordLabel = new JLabel("New password");
		New_passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(New_passwordLabel, "4, 8");
		
		New_passwordField = new JPasswordField();
		panel.add(New_passwordField, "8, 8, fill, default");
		
		Confirm_passwordLabel = new JLabel("Confirm new \r\npassword");
		panel.add(Confirm_passwordLabel, "4, 12");
		
		Confirm_passwordField = new JPasswordField();
		panel.add(Confirm_passwordField, "8, 12");
		
		JButton SubmitButton = new JButton("Submit");
		SubmitButton.setSize(110, 28);
		SubmitButton.setLocation(145, 222);
		contentPane.add(SubmitButton);
		SubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(NormalUser.changePassword(UserMenu.user, Old_passwordField.getText(), New_passwordField.getText(), Confirm_passwordField.getText())) {
					UserMenu.user.setPassword(New_passwordField.getText());
					dispose();
					UserMenu.setUser(null);
					Login.main(null);
				}
				
			}
		});
		getRootPane().setDefaultButton(SubmitButton);
	}
}
