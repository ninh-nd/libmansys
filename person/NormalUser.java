package person;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import menu.DatabaseManagement;

public class NormalUser extends User {

	private static final int MAXIMUM_NUMBER_OF_BOOKS = 5;

	public NormalUser(String username, String password, String name, String email, String address, String phoneNumber) {
		super(username, password, name, email, address, phoneNumber);
	}

//	public NormalUser(String username,String password) {
//		super(username,password);
//	}
	public static int countBooks(User user) {
		try (Connection conn = DatabaseManagement.connect()) {
			String sql = "SELECT count(book_id) AS count FROM renting WHERE (username = ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
	public static void FailedNotification(ArrayList<Integer> book_id) {
		try (Connection conn = DatabaseManagement.connect();) {
			String message = "Cannot rent:\n\n";
			for (int id : book_id) {
				String sql = "SELECT title,username,rented_date FROM books,renting WHERE (books.book_id = ?) AND books.book_id = renting.book_id";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					message = message + "- Book ID: " + String.valueOf(id) + " - " + rs.getString("title")
							+ " was rented by user " + rs.getString("username") + " on " + rs.getDate("rented_date")
							+ "\n";
				}
			}
			JOptionPane.showMessageDialog(null, message.trim(), null, JOptionPane.PLAIN_MESSAGE);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void SuccessfulNotification(ArrayList<Integer> book_id) {
		try (Connection conn = DatabaseManagement.connect()) {
			String message = "Books rented successfully\n\nBooks:\n";
			int no = 1;
			for (int id : book_id) {
				String sql = "SELECT title,username,rented_date FROM books,renting WHERE (books.book_id = ?) AND books.book_id = renting.book_id";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					message = message + String.valueOf(no) + ". Book ID: " + String.valueOf(id) + " - "
							+ rs.getString("title") + "\n";
				}
				no++;
			}
			JOptionPane.showMessageDialog(null, message.trim(), null, JOptionPane.PLAIN_MESSAGE);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static boolean rentBook(ArrayList<Integer> book_id, User user) {
		int cur_books = countBooks(user);
		if (book_id.size() + cur_books > MAXIMUM_NUMBER_OF_BOOKS) {
			JOptionPane.showMessageDialog(null,
					"You can only rent up to " + (MAXIMUM_NUMBER_OF_BOOKS - cur_books) + " books", null,
					JOptionPane.PLAIN_MESSAGE);
		} else {
			try (Connection conn = DatabaseManagement.connect();) {
				ArrayList<Integer> rented_id = new ArrayList<Integer>();
				ArrayList<Integer> free_id = new ArrayList<Integer>();
				for (int id : book_id) {
					String sql = "SELECT book_status FROM books WHERE (book_id = ?)";
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setInt(1, id);
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						String status = rs.getString("book_status");
						switch (status) {
						case "Available":
							free_id.add(id);
							break;
						case "Rented":
							rented_id.add(id);
							break;
						}
					}
				}
				if (free_id.size() == book_id.size()) {
					for (int id : free_id) {
						Date rented_date = Date.valueOf(LocalDate.now());
						Date due_date = Date.valueOf(LocalDate.now().plusDays(30));
						String sql = "UPDATE books SET book_status = 'Rented' WHERE (book_id = ?)";
						PreparedStatement stmt = conn.prepareStatement(sql);
						stmt.setInt(1, id);
						int rows = stmt.executeUpdate();
						if (rows == 0) {
							JOptionPane.showMessageDialog(null, "Cannot update database", null,
									JOptionPane.ERROR_MESSAGE);
							return false;
						}
						sql = "INSERT INTO renting (book_id,username,rented_date,due_date,is_extended) VALUES(?,?,?,?,'false')";
						stmt = conn.prepareStatement(sql);
						stmt.setInt(1, id);
						stmt.setString(2, user.getUsername());
						stmt.setDate(3, rented_date);
						stmt.setDate(4, due_date);
						rows = stmt.executeUpdate();
						if (rows == 0) {
							JOptionPane.showMessageDialog(null, "Cannot insert into database", null,
									JOptionPane.ERROR_MESSAGE);
							return false;
						}
						sql = "INSERT INTO history (book_id,username,rented_date,return_date) VALUES(?,?,?,NULL)";
						stmt = conn.prepareStatement(sql);
						stmt.setInt(1, id);
						stmt.setString(2, user.getUsername());
						stmt.setDate(3, rented_date);
						rows = stmt.executeUpdate();
						if (rows == 0) {
							JOptionPane.showMessageDialog(null, "Cannot insert into database", null,
									JOptionPane.ERROR_MESSAGE);
							return false;
						}
					}
					SuccessfulNotification(free_id);
					return true;
				} else {
					FailedNotification(rented_id);
					return false;
				}
					
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean returnBook(ArrayList<Integer> book_id, User user) {
		Date return_date = Date.valueOf(LocalDate.now());
		if (!book_id.isEmpty()) {
			try (Connection conn = DatabaseManagement.connect();) {
				for (int id : book_id) {
					String sql = "UPDATE history SET return_date = ? WHERE (book_id = ? AND username = ? AND return_date IS NULL)";
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setDate(1, return_date);
					stmt.setInt(2, id);
					stmt.setString(3, user.getUsername());
					int rows = stmt.executeUpdate();
					if (rows == 0) {
						JOptionPane.showMessageDialog(null, "Cannot update database", null, JOptionPane.ERROR_MESSAGE);
						return false;
					}
					sql = "UPDATE books SET book_status = ? WHERE (book_id = ?)";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(2, id);
					stmt.setString(1, "Available");
					rows = stmt.executeUpdate();
					if (rows == 0) {
						JOptionPane.showMessageDialog(null, "Cannot update database", null, JOptionPane.ERROR_MESSAGE);
						return false;
					}
					sql = "DELETE FROM renting WHERE (book_id = ?)";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, id);
					rows = stmt.executeUpdate();
					if (rows == 0) {
						JOptionPane.showMessageDialog(null, "Cannot delete from database", null,
								JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}
				JOptionPane.showMessageDialog(null, "Book returned successfully", null, JOptionPane.PLAIN_MESSAGE);
				return true;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void RenewNotification(ArrayList<Integer> extended_id, ArrayList<Integer> renew_id) {
		try (Connection conn = DatabaseManagement.connect()) {
			String message = new String();
			if (!renew_id.isEmpty()) {
				message += "Books renewed:\n";
				for (int id : renew_id) {
					String sql = "SELECT title FROM books WHERE (book_id = ?)";
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setInt(1, id);
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						message = message + "- Book ID: " + String.valueOf(id) + " - " + rs.getString("title") + "\n";
					}
				}
				String sql = "SELECT due_date FROM renting WHERE (book_id = ?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, renew_id.get(0));
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					message = message + "Due date extended to " + rs.getDate("due_date") + "\n";
				}
			}
			if (!extended_id.isEmpty()) {
				message += "\nCannot renew the following books:\n";
				for (int id : extended_id) {
					String sql = "SELECT due_date,title FROM books,renting WHERE (books.book_id = ?) AND (books.book_id = renting.book_id)";
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setInt(1, id);
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						LocalDate extend_date = rs.getDate("due_date").toLocalDate().minusDays(30);
						message = message + "- Book ID: " + String.valueOf(id) + " - " + rs.getString("title")
								+ " was renewed on " + extend_date + "\n";
					}
				}
				message += "<html><span style='color:red'>Note: A book can only be renewed once before return !</span></html>";
			}
			JOptionPane.showMessageDialog(null, message.trim(), null, JOptionPane.PLAIN_MESSAGE);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static boolean renewBook(ArrayList<Integer> book_id) {
		if (!book_id.isEmpty()) {
			try (Connection conn = DatabaseManagement.connect();) {
				ArrayList<Integer> extended_id = new ArrayList<Integer>(), renew_id = new ArrayList<Integer>();
				for (int id : book_id) {
					String sql = "SELECT is_extended FROM renting WHERE (book_id = ?)";
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setInt(1, id);
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						if (rs.getBoolean("is_extended"))
							extended_id.add(id);
						else {
							Date due_date = Date.valueOf(LocalDate.now().plusDays(30));
							sql = "UPDATE renting SET due_date = ?, is_extended = 'true' WHERE (book_id = ?)";
							stmt = conn.prepareStatement(sql);
							stmt.setDate(1, due_date);
							stmt.setInt(2, id);
							int rows = stmt.executeUpdate();
							if (rows == 0) {
								JOptionPane.showMessageDialog(null, "Cannot update database", null,
										JOptionPane.ERROR_MESSAGE);
								return false;
							}
							renew_id.add(id);
						}
					}
				}
				RenewNotification(extended_id, renew_id);
				return true;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return false;
	}

	private static boolean confirmPassword(String newPassword, String confirmPassword) {
		if (!newPassword.isEmpty() && !confirmPassword.isEmpty()) {
			if (!newPassword.equals(confirmPassword)) {
				JOptionPane.showMessageDialog(null, "Password does not match", null, JOptionPane.ERROR_MESSAGE);
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean changePassword(User user, String oldPassword, String newPassword, String confirmPassword) {
		
		if (oldPassword.trim().isEmpty() || newPassword.trim().isEmpty()|| confirmPassword.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Blank field", null, JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			if (!oldPassword.equals(user.getPassword())) {
				JOptionPane.showMessageDialog(null, "Old password is not correct", null, JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if (oldPassword.equals(user.getPassword())) {
				if (oldPassword.equals(newPassword)) {
					JOptionPane.showMessageDialog(null, "New password cannot be the same as old password", null, JOptionPane.ERROR_MESSAGE);
					return false;
				}
				else if (confirmPassword(newPassword, confirmPassword)) {
					try {
						String sql = "UPDATE users SET password = ? WHERE username = ?";
						Connection conn = DatabaseManagement.connect();
						PreparedStatement stmt = conn.prepareStatement(sql);
						stmt.setString(1, newPassword);
						stmt.setString(2, user.getUsername());
						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Change password successfully", null, JOptionPane.PLAIN_MESSAGE);
						return true;
					} catch (SQLException err) {
						System.out.println(err.getMessage());
					}
					return false;
				}
			}
			return false;
			}
			
	}
	
	public static void main(String[] args) {


//	public static void main(String[] args) {

//		NormalUser user = new NormalUser("an","andesu");
//		ArrayList<Integer> list1 = new ArrayList<Integer>(List.of(1,3,5,7));
//		ArrayList<Integer> list2 = new ArrayList<Integer>(List.of(2,4,6));
//		user.rentBook(list2, user);
//		user.renewBook(list1);
//		user.returnBook(list1, user);
//		changePassword("an", "andesu", "1", "1");
	}
}
