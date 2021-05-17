package usermenu;
import net.proteanit.sql.DbUtils;
import java.awt.EventQueue;
import java.sql.*;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import menu.DatabaseManagement;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import utilities.Search;

public class ViewBook {

	private JFrame frmBookList;
	private static DatabaseManagement db = new DatabaseManagement();
	private JTable table = new JTable();
	private JTextField searchField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBook window = new ViewBook();
					window.frmBookList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public ViewBook() {
		String sql = "SELECT * FROM books";
		try  {
            Connection conn = db.connect();
            PreparedStatement stmt = conn.prepareStatement(sql); 
            ResultSet rs = stmt.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException err) {
            System.out.println(err.getMessage());
		}
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBookList = new JFrame();
		frmBookList.setTitle("Book list");
		frmBookList.setVisible(true);
		frmBookList.setBounds(100, 100, 629, 377);
		frmBookList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBookList.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 145, 611, 193);
		frmBookList.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 611, 144);
		frmBookList.getContentPane().add(panel);
		panel.setLayout(null);
		
		searchField = new JTextField();
		searchField.setBounds(239, 47, 133, 20);
		panel.add(searchField);
		searchField.setColumns(10);
		
		JPanel optionPanel = new JPanel();
		optionPanel.setBounds(0, 5, 611, 36);
		panel.add(optionPanel);
		optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JRadioButton searchTitle = new JRadioButton("Search by title");
		optionPanel.add(searchTitle);
		
		
		JRadioButton searchCategory = new JRadioButton("Search by category");
		optionPanel.add(searchCategory);
		
		JRadioButton searchAuthor = new JRadioButton("Search by author");
		optionPanel.add(searchAuthor);
		
		
		JRadioButton searchPublisher = new JRadioButton("Search by publisher");
		optionPanel.add(searchPublisher);
		
		ButtonGroup group = new ButtonGroup(); //Add buttons into a group so that one can only choose one radiobox 
		group.add(searchTitle);
		group.add(searchPublisher);
		group.add(searchCategory);
		group.add(searchAuthor);
		
		JButton searchButton = new JButton("Search");
		frmBookList.getRootPane().setDefaultButton(searchButton); //Pressing Enter will also search
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (searchCategory.isSelected()) Search.searchCategory(searchField.getText(), table);
				
				if (searchTitle.isSelected()) Search.searchTitle(searchField.getText(), table);
				
				if (searchPublisher.isSelected()) Search.searchPublisher(searchField.getText(), table);
				
				if (searchAuthor.isSelected()) Search.searchAuthor(searchField.getText(), table);
				
				if (searchField.getText().isBlank()) Search.showAllBook(table); //Return list of all book if the search field is empty
			}
		});
		searchButton.setBounds(261, 93, 89, 23);
		panel.add(searchButton);
		
		
	}
}
