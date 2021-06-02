package utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import menu.DatabaseManagement;
import person.User;

public class ViewRentedBook {
	public static ResultSet viewUserRentedBook(User user) {
		String sql = "Select r.book_id, b.title, r.username, r.rented_date, r.due_date, r.is_extended FROM renting r, books b WHERE r.book_id = b.book_id AND username = ?";
		try {
			String username = user.getUsername();
			Connection conn = DatabaseManagement.connect();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			return rs;
		} catch (Exception err) {			
			System.out.println(err.getMessage());
		}
		return null;
	}
	public static ResultSet viewRentedBook() {
		String sql = "Select r.book_id, b.title, r.username, r.rented_date, r.due_date, r.is_extended FROM renting r, books b WHERE r.book_id = b.book_id";
		try {
			Connection conn = DatabaseManagement.connect();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			return rs;
		} catch (Exception err) {			
			System.out.println(err.getMessage());
		}
		return null;
	}
}
