package adminmenu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import book.info.Book;
import menu.DatabaseManagement;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddCategory extends JFrame {

	private JPanel contentPane;
	private JTextField categoryField;
	private static DatabaseManagement db = new DatabaseManagement();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCategory frame = new AddCategory();
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
	public AddCategory() {
		setTitle("New category");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 83, 434, 80);
		contentPane.add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(122dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel inputLabel = new JLabel("Input new category");
		panel.add(inputLabel, "6, 4");
		
		categoryField = new JTextField();
		panel.add(categoryField, "12, 4, fill, default");
		categoryField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 164, 434, 97);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String category = categoryField.getText();
				if (!category.isBlank()) {
					try {
						String sql = "SELECT cat_name from category WHERE cat_name = ?";
						Connection conn = db.connect();
						PreparedStatement stmt = conn.prepareStatement(sql);
						stmt.setString(1, category);
						ResultSet rs = stmt.executeQuery();
						if (rs.next()) {
							JOptionPane.showMessageDialog(null, "Category already existed", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
						else {
							String sql2 = "INSERT INTO category(cat_name) VALUES (?)";
							PreparedStatement stmt2 = conn.prepareStatement(sql2);
							stmt2.setString(1, category);
							stmt2.executeUpdate();
							JOptionPane.showMessageDialog(null, "Category added", null, JOptionPane.PLAIN_MESSAGE);
						}
				} catch (SQLException err) {
					System.out.println(err.getMessage());
				}
				}
				}
			});
		submitButton.setBounds(172, 37, 89, 23);
		panel_1.add(submitButton);
		}
	}
