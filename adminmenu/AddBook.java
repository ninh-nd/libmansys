package adminmenu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import book.info.Book;
import person.Librarian;
import utilities.ViewBook;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class AddBook {

	private JFrame frmAddBook;
	private JTextField titleField;
	private JLabel authorLabel;
	private JTextField authorField;
	private JLabel categoryLabel;
	private JLabel publisherLabel;
	private JTextField publisherField;
	private JComboBox categoryField;

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
		frmAddBook.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("81px"),
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("171px"),},
			new RowSpec[] {
				RowSpec.decode("31px"),
				RowSpec.decode("20px"),
				RowSpec.decode("31px"),
				RowSpec.decode("20px"),
				RowSpec.decode("34px"),
				RowSpec.decode("20px"),
				RowSpec.decode("34px"),
				RowSpec.decode("20px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("23px"),}));
		
		JLabel titleLabel = new JLabel("Title");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmAddBook.getContentPane().add(titleLabel, "2, 2, fill, center");
		
		titleField = new JTextField();
		frmAddBook.getContentPane().add(titleField, "4, 2, fill, top");
		titleField.setColumns(10);
		
		authorLabel = new JLabel("Author");
		authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmAddBook.getContentPane().add(authorLabel, "2, 4, fill, center");
		
		authorField = new JTextField();
		frmAddBook.getContentPane().add(authorField, "4, 4, fill, top");
		authorField.setColumns(10);
		
		categoryLabel = new JLabel("Category");
		categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmAddBook.getContentPane().add(categoryLabel, "2, 6, center, top");
		
		Vector<String> categoryList = new Vector<String>();
		for (int i=0; i<Book.categoryList.size(); i++) {
			categoryList.add(Book.categoryList.get(i));
		}
		categoryField = new JComboBox<String>(categoryList);
		frmAddBook.getContentPane().add(categoryField, "4, 6, fill, default");
		
		
		publisherLabel = new JLabel("Publisher");
		publisherLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmAddBook.getContentPane().add(publisherLabel, "2, 8, fill, center");
		
		publisherField = new JTextField();
		frmAddBook.getContentPane().add(publisherField, "4, 8, fill, top");
		publisherField.setColumns(10);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Librarian.addBook(titleField.getText(), authorField.getText(), categoryField.getSelectedItem().toString(), publisherField.getText())) {
					frmAddBook.dispose();
					ViewBook.main(null);
				}
			}
		});
		frmAddBook.getContentPane().add(submitButton, "4, 10, right, top");
	}
}
