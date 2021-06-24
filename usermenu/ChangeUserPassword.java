package usermenu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import person.NormalUser;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JPasswordField;

public class ChangeUserPassword extends JFrame {

	private JPanel contentPane;
	private JLabel New_passwordLabel;
	private JLabel Confirm_passwordLabel;
	private JPasswordField Old_passwordField;
	private JPasswordField New_passwordField;
	private JPasswordField Confirm_passwordField;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 417, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 403, 216);
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
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
		
		JLabel Old_passwordLabel = new JLabel("Old password");
		panel.add(Old_passwordLabel, "4, 10");
		
		Old_passwordField = new JPasswordField();
		panel.add(Old_passwordField, "8, 10, 10, 1, fill, default");
		
		New_passwordLabel = new JLabel("New password");
		panel.add(New_passwordLabel, "4, 16");
		
		New_passwordField = new JPasswordField();
		panel.add(New_passwordField, "8, 16, 10, 1, fill, default");
		
		Confirm_passwordLabel = new JLabel("Confirm new \r\npassword");
		panel.add(Confirm_passwordLabel, "4, 22");
		
		Confirm_passwordField = new JPasswordField();
		panel.add(Confirm_passwordField, "8, 22, 10, 1");
		
		JPanel submitpanel = new JPanel();
		submitpanel.setBounds(0, 215, 403, 38);
		contentPane.add(submitpanel);
		
		JButton SubmitButton = new JButton("Submit");
		submitpanel.add(SubmitButton);
		SubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(NormalUser.changePassword(UserMenu.user, Old_passwordField.getText(), New_passwordField.getText(), Confirm_passwordField.getText())) {
					dispose();
					UserMenu.frmUserFunctions.setState(Frame.NORMAL);
				}
				
			}
		});
		getRootPane().setDefaultButton(SubmitButton);
	}
}
