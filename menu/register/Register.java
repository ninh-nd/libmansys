package menu.register;
import java.sql.*;

import menu.DatabaseManagement;
import system.*;

public class Register {
	private DatabaseManagement db = new DatabaseManagement();
    /**
     * Create new account into database
     */
    public void register(String username, String password, String name, String email, String phone, String address, String confirmPassword) {
        try (Connection conn = db.connect();) {
            if (username != null && password != null && confirmPassword != null) {
                if (password.equals(confirmPassword)) {
                    String sql = "INSERT INTO users (`username`,`password`,`name`,`email`,`phone`,`address` ) VALUE (?,?,?,?,?,?)";
                    
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    stmt.setString(3, name);
                    stmt.setString(4, email);
                    stmt.setString(5, phone);
                    stmt.setString(6, address);
                    stmt.executeUpdate(sql);
                } else {
                    System.out.print("Password is not match");
                }
            } else {
                System.out.print("Blanked field");
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }
}