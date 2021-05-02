package menu.register;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class Register {
    private static final String url = "jdbc:postgresql://localhost/Library";
    private static final String user = "postgres";
    private static final String password = "123456";
    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton exitButton;
    private JLabel RegisterMenuLabel;

    /**
     * Establish connection to the database
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    /**
     * create new account into database
     */
    private void Check(String username, String password1, String password2) {
        try {
            if (username != null && password1 != null && password2 != null) {
                if (password1.equals(password2)) {
                    String sql = "INSERT INTO users (`username`,`password`) VALUE (?,?)";
                    Connection conn = connect();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, username);
                    stmt.setString(2, password1);
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

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Register window = new Register();
                    window.connect();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Register() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        
    }
}