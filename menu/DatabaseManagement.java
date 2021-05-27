package menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManagement {
	private static final String url = "jdbc:postgresql://localhost/Library";
	private static final String user = "postgres";
	private static final String password = "123456";
	/**
	 * Establish connection to the database
	 */
	public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
