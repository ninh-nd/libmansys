package person;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import book.*;
import net.proteanit.sql.DbUtils;
import person.*;
import menu.DatabaseManagement;

public class Reader extends User{
	
	private static DatabaseManagement db = new DatabaseManagement();

	public Reader(String username, String password) {
		super(username, password);
	}
	
	private void reserveBook(int bookId, String name, Date reserveDate) {

	}

	private void rentBook(int bookId, String username, Date rentDate, Date dueDate) {
		if (username.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Blank field", null, JOptionPane.ERROR_MESSAGE);			
		}else {
				try {
					String sql = "INSERT INTO renting (book_id,username,rented_date,due_date) VALUES (?,?,?,?)";
					Connection conn = db.connect();
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setInt(1, bookId);
					stmt.setString(2, username);
					stmt.setDate(3, rentDate);
					stmt.setDate(3, dueDate);
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Book added", null, JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException err) {
					System.out.println(err.getMessage());
				}
			}
				
	}

	private void returnBook() {

	}

	private void renewBook(int BookId, String name, Date rentDate, Date dueDate) {
		
	}
	
	private void myBook(String username) {
		
		if (!username.trim().isEmpty()) {
			try (Connection conn = db.connect();) {
				String sql = "SELECT * FROM renting WHERE (username = ?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
				// print the result to window 
				}
			} catch (SQLException err) {
				System.out.println(err.getMessage());
			}
			
		} 
	}
}
