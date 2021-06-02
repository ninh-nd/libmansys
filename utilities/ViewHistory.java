package utilities;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import menu.DatabaseManagement;
import net.proteanit.sql.DbUtils;

public class ViewHistory {
	private JFrame frmHistoryBookList;
	private JTable table = new JTable();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewHistory window = new ViewHistory();
					window.frmHistoryBookList.setVisible(true);					
				} catch (Exception err) {
					System.out.println(err.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public void ViewHistory() {
		String sql = "Select h.username, h.book_id, b.title, h.rented_date, h.return_date FROM history h, books b WHERE h.book_id = b.book_id ";
		try {
			Connection conn = DatabaseManagement.connect();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception err) {			
			System.out.println(err.getMessage());
		}
		initialize();
	}
	
	private void initialize() {
		frmHistoryBookList = new JFrame();
		frmHistoryBookList.setTitle("History list");
		frmHistoryBookList.setVisible(true);
		frmHistoryBookList.setBounds(100, 100, 788, 377);
		frmHistoryBookList.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHistoryBookList.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 145, 772, 193);
		frmHistoryBookList.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 772, 144);
		frmHistoryBookList.getContentPane().add(panel);
		panel.setLayout(null);

		
	}
}
