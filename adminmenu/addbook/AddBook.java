package adminmenu.addbook;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import person.Librarian;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AddBook {

	private JFrame frmAddBook;
	private JTextField titleField;
	private JLabel authorLabel;
	private JTextField authorField;
	private JTextField categoryField;
	private JLabel categoryLabel;
	private JLabel publisherLabel;
	private JTextField publisherField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook window = new AddBook();
					window.frmAddBook.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddBook() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddBook = new JFrame();
		frmAddBook.setTitle("Add book");
		frmAddBook.setBounds(100, 100, 450, 300);
		frmAddBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddBook.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("Title");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(10, 34, 81, 14);
		frmAddBook.getContentPane().add(titleLabel);
		
		titleField = new JTextField();
		titleField.setBounds(101, 31, 160, 20);
		frmAddBook.getContentPane().add(titleField);
		titleField.setColumns(10);
		
		authorLabel = new JLabel("Author");
		authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		authorLabel.setBounds(10, 85, 81, 14);
		frmAddBook.getContentPane().add(authorLabel);
		
		authorField = new JTextField();
		authorField.setBounds(101, 82, 160, 20);
		frmAddBook.getContentPane().add(authorField);
		authorField.setColumns(10);
		
		categoryLabel = new JLabel("Category");
		categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		categoryLabel.setBounds(10, 136, 81, 14);
		frmAddBook.getContentPane().add(categoryLabel);
		
		categoryField = new JTextField();
		categoryField.setBounds(101, 133, 160, 20);
		frmAddBook.getContentPane().add(categoryField);
		categoryField.setColumns(10);
		
		publisherLabel = new JLabel("Publisher");
		publisherLabel.setHorizontalAlignment(SwingConstants.CENTER);
		publisherLabel.setBounds(10, 187, 81, 14);
		frmAddBook.getContentPane().add(publisherLabel);
		
		publisherField = new JTextField();
		publisherField.setBounds(101, 184, 160, 20);
		frmAddBook.getContentPane().add(publisherField);
		publisherField.setColumns(10);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Librarian.addBook(titleField.getText(), authorField.getText(), categoryField.getText(), publisherField.getText());
			}
		});
		submitButton.setBounds(172, 215, 89, 23);
		frmAddBook.getContentPane().add(submitButton);
	}
}
