package jm.task.core.jdbc.util;
import java.sql.*;

public class Util {
    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1/mydbtest";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String NAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    public Util() {
    }

    public static Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
