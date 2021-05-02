package menu.register;
import java.sql.*;

import menu.DatabaseManagement;

public class Register {
	private DatabaseManagement db = new DatabaseManagement();
    /**
     * Create new account into database
     */
    private void confirmPassword(String username, String password, String confirmPassword) {
        try (Connection conn = db.connect();) {
            if (username != null && password != null && confirmPassword != null) {
                if (password.equals(confirmPassword)) {
                    String sql = "INSERT INTO users (`username`,`password`) VALUE (?,?)";
                    
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, username);
                    stmt.setString(2, password);
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