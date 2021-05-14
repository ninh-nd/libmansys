package person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import book.info.Book;
import menu.DatabaseManagement;

public class Librarian extends User {
	private static DatabaseManagement db = new DatabaseManagement();
	public Librarian(String username, String password) {
		super(username, password);
	}
	private void removeBook(Book book) {
		//Set quantity to 0
	}
	private void updateBook(Book book) {
		
	}
	private void sendNotification(String message) {
		
	}
	private boolean duplicateBook(String title) {
		if (!title.trim().isEmpty()) {
			try (Connection conn = db.connect();) {
				String sql = "SELECT title FROM books WHERE (username = ?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, title);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					JOptionPane.showMessageDialog(null, "Duplicate book", null, JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} catch (SQLException err) {
				System.out.println(err.getMessage());
			}
			return true;
		} else
			return false;
	}
	
	private void addBook(String title, String author, String category, String publisher) {
				
		if (title.trim().isEmpty() || author.trim().isEmpty() || category.trim().isEmpty()
				|| publisher.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Blank field", null, JOptionPane.ERROR_MESSAGE);			
		}else {
			if (duplicateBook(title) ) {
				try {
					String sql = "INSERT INTO books (title,author,category,publisher) VALUES (?,?,?,?)";
					Connection conn = db.connect();
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, title);
					stmt.setString(2, author);
					stmt.setString(3, category);
					stmt.setString(4, publisher);
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Book added", null, JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException err) {
					System.out.println(err.getMessage());
				}
			}
		}
		
	}
	
	private static void removeBook(String title) {
		if(title.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Blank field", null, JOptionPane.ERROR_MESSAGE);
		}else try {
			String sql = "UPDATE books SET quantity = ? WHERE (title = ?)";
			Connection conn = db.connect();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 0);
			stmt.setString(2, title);
			
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Book added", null, JOptionPane.PLAIN_MESSAGE);
		} catch (SQLException err) {
			System.out.println(err.getMessage());
		}
		
	}
	public static void main(String[] args) {
		removeBook("A Christmas Carol");
	}
}
