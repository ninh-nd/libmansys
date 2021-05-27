package person;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import book.info.Book;
import menu.DatabaseManagement;

public class Reader extends User{
	private static DatabaseManagement db = new DatabaseManagement();
	public Reader(String username, String password) {
		super(username, password);
	}
	
	public void rentBook(int book_id) {
		try(Connection conn = db.connect();){
			String sql = "SELECT book_status FROM books WHERE (book_id = ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, book_id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				String status = rs.getString("book_status");
				switch(status) {
				case "Available":
					Date rented_date = Date.valueOf(LocalDate.now());
					Date due_date = Date.valueOf(LocalDate.now().plusDays(30));
					sql = "UPDATE books SET book_status = 'Rented' WHERE (book_id = ?)";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, book_id);
					int rows = stmt.executeUpdate();
					if(rows == 0) {
						JOptionPane.showMessageDialog(null, "Cannot update database", null, JOptionPane.ERROR_MESSAGE);
						return;}
					sql = "INSERT INTO renting (book_id,username,rented_date,due_date,is_extended) VALUES(?,?,?,?,'false')";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, book_id);
					stmt.setString(2, getUsername());
					stmt.setDate(3, rented_date);
					stmt.setDate(4, due_date);
					rows = stmt.executeUpdate();
					if(rows == 0) {
						JOptionPane.showMessageDialog(null, "Cannot insert into database", null, JOptionPane.ERROR_MESSAGE);
						return;}
					sql = "INSERT INTO history (book_id,username,rented_date,return_date) VALUES(?,?,?,NULL)";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, book_id);
					stmt.setString(2, getUsername());
					stmt.setDate(3, rented_date);
					rows = stmt.executeUpdate();
					if(rows == 0) {
						JOptionPane.showMessageDialog(null, "Cannot insert into database", null, JOptionPane.ERROR_MESSAGE);
						return;}
					JOptionPane.showMessageDialog(null, "Book is rented by "+getUsername(), null, JOptionPane.PLAIN_MESSAGE);
					break;
				case "Rented":
					sql = "SELECT rented_date,username FROM renting WHERE (book_id = ?)";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1,book_id);
					ResultSet rs2 = stmt.executeQuery();
					while(rs2.next()) {
						rented_date = rs2.getDate("rented_date");
						String username = rs2.getString("username");
						JOptionPane.showMessageDialog(null,"This book was rented on "+rented_date+" by user: " +username, null, JOptionPane.PLAIN_MESSAGE);
					}
					break;
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void returnBook(int book_id) {
		Date return_date = Date.valueOf(LocalDate.now());
		try(Connection conn = db.connect();){
			String sql = "UPDATE history SET return_date = ? WHERE (book_id = ? AND username = ? AND return_date IS NULL)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDate(1, return_date);
			stmt.setInt(2, book_id);
			stmt.setString(3, getUsername());
			int rows = stmt.executeUpdate();
			if(rows == 0) {
				JOptionPane.showMessageDialog(null, "Cannot update database", null, JOptionPane.ERROR_MESSAGE);
				return;}
			sql = "UPDATE books SET book_status = ? WHERE (book_id = ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(2, book_id);
			stmt.setString(1, "Available");
			rows = stmt.executeUpdate();
			if(rows == 0) {
				JOptionPane.showMessageDialog(null, "Cannot update database", null, JOptionPane.ERROR_MESSAGE);
				return;}
			sql = "DELETE FROM renting WHERE (book_id = ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, book_id);
			rows = stmt.executeUpdate();
			if(rows == 0) {
				JOptionPane.showMessageDialog(null, "Cannot delete from database", null, JOptionPane.ERROR_MESSAGE);
				return;}
			JOptionPane.showMessageDialog(null, "Book returned successfully", null, JOptionPane.PLAIN_MESSAGE);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void renewBook(int book_id) {
		try(Connection conn = db.connect();){
			String sql = "SELECT due_date,is_extended FROM renting WHERE (book_id = ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, book_id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				if(rs.getBoolean("is_extended")) {
					LocalDate extend_date = rs.getDate("due_date").toLocalDate().minusDays(30);
					JOptionPane.showMessageDialog(null, "You have already extended this book on "+extend_date, null, JOptionPane.PLAIN_MESSAGE);
				}
				else {
				Date due_date = Date.valueOf(LocalDate.now().plusDays(30));
				sql = "UPDATE renting SET due_date = ?, is_extended = 'true' WHERE (book_id = ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setDate(1, due_date);
				stmt.setInt(2, book_id);
				int rows = stmt.executeUpdate();
				if(rows == 0) {
					JOptionPane.showMessageDialog(null, "Cannot update database", null, JOptionPane.ERROR_MESSAGE);
					return;}
				else JOptionPane.showMessageDialog(null, "Due date extended to "+due_date, null, JOptionPane.PLAIN_MESSAGE);}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Reader reader = new Reader("an","andesu");
		int book_id = 1;
		reader.rentBook(book_id);
		reader.renewBook(book_id);
		reader.returnBook(book_id);
	}
}
