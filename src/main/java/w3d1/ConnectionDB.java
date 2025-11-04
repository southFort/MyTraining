package w3d1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/my_students";
    private static final String USER = "madmin";
    private static final String PASSWORD = "2510";
    private Connection conn;

    /**
     * Метод соединения с БД
     */
    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e.getMessage());
        }

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection create");
        return conn;
    }

}
