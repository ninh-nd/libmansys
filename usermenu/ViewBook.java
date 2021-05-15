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

public class ViewBook {

	private JFrame frmBookList;
	private static DatabaseManagement db = new DatabaseManagement();
	private JTable table = new JTable();
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
		frmBookList.setBounds(100, 100, 627, 232);
		frmBookList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBookList.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 611, 193);
		frmBookList.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
	}
}
