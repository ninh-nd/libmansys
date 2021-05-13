package utilities;
import java.sql.*;

import menu.DatabaseManagement;
public class Search {
	private static DatabaseManagement db = new DatabaseManagement();
	public static void searchTitle(String title) {
		if(!title.trim().isEmpty()) {
			try(Connection conn	= db.connect();){
				//String process: Convert the search string to all lowercase
				title = title.toLowerCase();
				String sql = "SELECT * FROM books WHERE LOWER(title) LIKE ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + title + "%");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					//Printing results to the window
				}
				stmt.close();
				
			}catch (SQLException err) {
				System.out.println(err.getMessage());
			}
		}
	}
	public static void searchCategory(String category) {
		if(!category.trim().isEmpty()) {
			try(Connection conn	= db.connect();){
				//String process: Convert the search string to all lowercase
				category = category.toLowerCase();
				String sql = "SELECT * FROM books WHERE LOWER(category) LIKE ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + category + "%");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					//Printing results to the window
				}
				stmt.close();
				
			}catch (SQLException err) {
				System.out.println(err.getMessage());
			}
		}
	}
	public static void searchPublisher(String publisher) {
		if(!publisher.trim().isEmpty()) {
			try(Connection conn	= db.connect();){
				//String process: Convert the search string to all lowercase
				publisher = publisher.toLowerCase();
				String sql = "SELECT * FROM books WHERE LOWER(category) LIKE ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + publisher + "%");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					//Printing results to the window
				}
				stmt.close();
				
			}catch (SQLException err) {
				System.out.println(err.getMessage());
			}
		}
	}
	public static void main(String[] args) {
		
	}

}
