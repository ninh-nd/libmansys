package usermenu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class RentBook extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField periodField;
	private JTextField rentDateField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentBook frame = new RentBook();
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
	public RentBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 206);
		contentPane.add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:max(82dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(86dlu;default)"),},
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel book_idLabel = new JLabel("Book ID");
		panel.add(book_idLabel, "6, 4");
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textField, "10, 4, fill, default");
		textField.setColumns(10);
		
		JLabel periodLabel = new JLabel("Period");
		panel.add(periodLabel, "6, 8");
		
		periodField = new JTextField();
		periodField.setHorizontalAlignment(SwingConstants.CENTER);
		periodField.setText("30");
		periodField.setEditable(false);
		panel.add(periodField, "10, 8, fill, default");
		periodField.setColumns(10);
		
		JLabel rentDateLabel = new JLabel("Rent date (YYYY-MM-DD)");
		panel.add(rentDateLabel, "6, 12");
		
		rentDateField = new JTextField();
		rentDateField.setHorizontalAlignment(SwingConstants.CENTER);
		rentDateField.setText(Date.valueOf(LocalDate.now()).toString());
		rentDateField.setEditable(false);
		panel.add(rentDateField, "10, 12, fill, default");
		rentDateField.setColumns(10);
		
		JPanel submitPanel = new JPanel();
		submitPanel.setBounds(0, 217, 434, 33);
		contentPane.add(submitPanel);
		
		JButton submitButton = new JButton("Submit");
		submitPanel.add(submitButton);
	}
}
