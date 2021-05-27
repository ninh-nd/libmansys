package utilities;

import java.sql.*;

import javax.swing.JTable;

import menu.DatabaseManagement;
import net.proteanit.sql.DbUtils;

public class Search {
	public static ResultSet searchTitle(String title) {
		if (!title.trim().isEmpty()) {
			try (Connection conn = DatabaseManagement.connect();) {
				// String process: Convert the search string to all lowercase
				title = title.toLowerCase();
				String sql = "SELECT * FROM books WHERE LOWER(title) LIKE ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + title + "%");
				ResultSet rs = stmt.executeQuery();
				return rs;
			} catch (SQLException err) {
				System.out.println(err.getMessage());
			}
		}
		return null;
	}

	public static ResultSet searchCategory(String category) {
		if (!category.trim().isEmpty()) {
			try (Connection conn = DatabaseManagement.connect();) {
				// String process: Convert the search string to all lowercase
				category = category.toLowerCase();
				String sql = "SELECT * FROM books WHERE LOWER(category) LIKE ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + category + "%");
				ResultSet rs = stmt.executeQuery();
				return rs;
			} catch (SQLException err) {
				System.out.println(err.getMessage());
			}
		}
		return null;
	}

	public static ResultSet searchPublisher(String publisher) {
		if (!publisher.trim().isEmpty()) {
			try (Connection conn = DatabaseManagement.connect();) {
				// String process: Convert the search string to all lowercase
				publisher = publisher.toLowerCase();
				String sql = "SELECT * FROM books WHERE LOWER(publisher) LIKE ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + publisher + "%");
				ResultSet rs = stmt.executeQuery();
				return rs;
			} catch (SQLException err) {
				System.out.println(err.getMessage());
			}
		}
		return null;
	}

	public static ResultSet searchAuthor(String author) {
		if (!author.trim().isEmpty()) {
			try (Connection conn = DatabaseManagement.connect();) {
				// String process: Convert the search string to all lowercase
				author = author.toLowerCase();
				String sql = "SELECT * FROM books WHERE LOWER(author) LIKE ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + author + "%");
				ResultSet rs = stmt.executeQuery();
				return rs;
			} catch (SQLException err) {
				System.out.println(err.getMessage());
			}
		}
		return null;
	}

	public static ResultSet showAllBook() {
		try (Connection conn = DatabaseManagement.connect();) {
			String sql = "SELECT * FROM books";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			return rs;
		} catch (SQLException err) {
			System.out.println(err.getMessage());
		}
		return null;
	}

	public static void main(String[] args) {

	}

}
