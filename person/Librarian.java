package person;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import book.info.Book;
import menu.DatabaseManagement;
import utilities.ViewBook;

public class Librarian extends User {
	private static DatabaseManagement db = new DatabaseManagement();
	
	public Librarian(String username, String password) {
		super(username, password);
	}
	
	private void updateBook(Book book) {
		
	}
	private void sendNotification(String message) {
		
	}
	private static boolean duplicateBook(String title) {
		if (!title.trim().isEmpty()) {
			try (Connection conn = db.connect();) {
				String sql = "SELECT title FROM books WHERE (title = ?)";
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
	
	public static boolean addBook(String title, String author, String category, String publisher) {
				
		if (title.trim().isEmpty() || author.trim().isEmpty() || category.trim().isEmpty()
				|| publisher.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Blank field", null, JOptionPane.ERROR_MESSAGE);			
		}else {
			if (duplicateBook(title) ) {
				try {
					String setMaxID = "SELECT setval(pg_get_serial_sequence('books', 'book_id'), max(book_id)) FROM books"; //Correct the ID counter
					String sql = "INSERT INTO books (title,author,category,publisher, book_status) VALUES (?,?,?,?, ?)";
					Connection conn = db.connect();
					PreparedStatement stmt = conn.prepareStatement(sql);
					PreparedStatement stmt2 = conn.prepareStatement(setMaxID);
					stmt2.execute();
					stmt.setString(1, title);
					stmt.setString(2, author);
					stmt.setString(3, category);
					stmt.setString(4, publisher);
					stmt.setString(5, "Available");
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Book added", null, JOptionPane.PLAIN_MESSAGE);
					return true;
				} catch (SQLException err) {
					System.out.println(err.getMessage());
				}
			}
		}
		return false;
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
			JOptionPane.showMessageDialog(null, "Removed", null, JOptionPane.PLAIN_MESSAGE);
		} catch (SQLException err) {
			System.out.println(err.getMessage());
		}
		
	}
	private void issuedBook(int book_id, String username, Date dueDate) {
		if (username.trim().isEmpty() ) {
			JOptionPane.showMessageDialog(null, "Blank field", null, JOptionPane.ERROR_MESSAGE);			
		}else {			
				try {
					String sql = "INSERT INTO renting (due_date) VALUES (?) WHERE book_id = ? AND username = ?";
					Connection conn = db.connect();
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setDate(1, dueDate);
					stmt.setInt(2, book_id);
					stmt.setString(3,username);
									
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Created", null, JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException err) {
					System.out.println(err.getMessage());
				}
			}
		
		
	}
	
	private void viewIssuedBooks() {
		try {
			String sql = "SELECT * from renting";
			Connection conn = db.connect();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				//Printing results to the window
			}
			stmt.close();
			
		} catch (Exception err) {
			System.out.println(err.getMessage());
		}
		
	}
	
	
	
	public static void main(String[] args) {
		//removeBook("A Christmas Carol");
		
		
	}
}
