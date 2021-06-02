package utilities;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import menu.DatabaseManagement;
import net.proteanit.sql.DbUtils;

public class ViewRentedBook {
	private JFrame frmRentedBookList;
	private JTable table = new JTable();
	private JTextField searchField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRentedBook window = new ViewRentedBook();
					window.frmRentedBookList.setVisible(true);					
				} catch (Exception err) {
					System.out.println(err.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public void ViewRentedBooks() {
		String sql = "Select r.book_i, b.title, r.username, r.rented_date, r.due_date, r.is_extended FROM renting r, books b WHERE r.book_id = b.book_id";
		
		try {
			Connection conn = DatabaseManagement.connect();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception err) {
			// TODO: handle exception
			System.out.println(err.getMessage());
		}
		initialize();
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRentedBookList = new JFrame();
		frmRentedBookList.setTitle("Rented Book list");
		frmRentedBookList.setVisible(true);
		frmRentedBookList.setBounds(100, 100, 788, 377);
		frmRentedBookList.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRentedBookList.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 145, 772, 193);
		frmRentedBookList.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 772, 144);
		frmRentedBookList.getContentPane().add(panel);
		panel.setLayout(null);


		



		
		
	}

}
