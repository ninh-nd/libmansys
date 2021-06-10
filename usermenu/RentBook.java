package usermenu;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import person.NormalUser;
import utilities.Search;
import utilities.ViewBook;

public class RentBook extends JFrame {

	private JFrame frmBookList;
	private JTable table = new JTable() {
		@Override
		public Class getColumnClass(int column) {
			return (getValueAt(0, column).getClass());
		} // Render true/false as checkboxes

		@Override
		public boolean isCellEditable(int row, int column) {
			if (column == table.getColumnCount() - 1)
				return true;
			else
				return false;
		}
	};
	// Generate array of checkboxes
	private static Vector<Boolean> checkbox = new Vector<Boolean>();
	private JTextField searchField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentBook window = new RentBook();
					window.frmBookList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RentBook() {
		frmBookList = new JFrame();
		frmBookList.setTitle("Book list");
		frmBookList.setVisible(true);
		frmBookList.setBounds(100, 100, 788, 507);
		frmBookList.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBookList.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 145, 772, 193);
		frmBookList.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		DefaultTableModel tableModel = (DefaultTableModel) DbUtils.resultSetToTableModel(ViewBook.viewBookAvailable());
		checkbox.clear();
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			checkbox.add(false);
		}
		tableModel.addColumn("Select", checkbox);
		table.setModel(tableModel);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 772, 144);
		frmBookList.getContentPane().add(panel);
		panel.setLayout(null);

		searchField = new JTextField();
		searchField.setBounds(276, 47, 219, 20);
		panel.add(searchField);
		searchField.setColumns(10);

		JPanel optionPanel = new JPanel();
		optionPanel.setBounds(0, 5, 772, 36);
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

		ButtonGroup group = new ButtonGroup(); // Add buttons into a group so that one can only choose one radiobox
		group.add(searchTitle);
		group.add(searchPublisher);
		group.add(searchCategory);
		group.add(searchAuthor);

		JButton searchButton = new JButton("Search");
		frmBookList.getRootPane().setDefaultButton(searchButton); // Pressing Enter will also search
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (searchCategory.isSelected() && !searchField.getText().isBlank()) {
					DefaultTableModel tableModel = (DefaultTableModel) DbUtils
							.resultSetToTableModel(Search.searchCategory(searchField.getText()));
					tableModel.addColumn("Select", checkbox);
					table.setModel(tableModel);
				}
				if (searchTitle.isSelected() && !searchField.getText().isBlank()) {
					DefaultTableModel tableModel = (DefaultTableModel) DbUtils
							.resultSetToTableModel(Search.searchTitle(searchField.getText()));
					tableModel.addColumn("Select", checkbox);
					table.setModel(tableModel);
				}
				if (searchPublisher.isSelected() && !searchField.getText().isBlank()) {
					DefaultTableModel tableModel = (DefaultTableModel) DbUtils
							.resultSetToTableModel(Search.searchPublisher(searchField.getText()));
					tableModel.addColumn("Select", checkbox);
					table.setModel(tableModel);
				}

				if (searchAuthor.isSelected() && !searchField.getText().isBlank()) {
					DefaultTableModel tableModel = (DefaultTableModel) DbUtils
							.resultSetToTableModel(Search.searchAuthor(searchField.getText()));
					tableModel.addColumn("Select", checkbox);
					table.setModel(tableModel);
				}

				if (searchField.getText().isBlank()) {
					DefaultTableModel tableModel = (DefaultTableModel) DbUtils
							.resultSetToTableModel(Search.showAllBook());
					tableModel.addColumn("Select", checkbox);
					table.setModel(tableModel);
				}
			}
		});
		searchButton.setBounds(341, 78, 89, 23);
		panel.add(searchButton);

		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Integer> rentList = new ArrayList<Integer>();
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					if ((boolean) table.getModel().getValueAt(i, 6))
						rentList.add((Integer) table.getModel().getValueAt(0, i));
				}
				if (NormalUser.returnBook(rentList, UserMenu.user))
					dispose();
			}
		});
		submitButton.setBounds(341, 389, 89, 23);
		frmBookList.getContentPane().add(submitButton);
	}
}
