package utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import menu.DatabaseManagement;
import person.User;

public class ViewHistory {
	public static ResultSet viewUserHistory(User user) {
		String sql = "Select h.book_id, h.username, b.title, h.rented_date, h.return_date FROM history h, books b WHERE h.book_id = b.book_id AND username = ?";
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
	public static ResultSet viewHistory() {
		String sql = "Select h.book_id, h.username, b.title, h.rented_date, h.return_date FROM history h, books b WHERE h.book_id = b.book_id";
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
