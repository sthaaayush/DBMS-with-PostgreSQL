package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = "jdbc:postgresql://localhost:5432/testdb";
	private static final String USER = "postgres";
	private static final String PASSWORD = "root";
	private static Connection connection = null;

	public static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connected to PostgreSQL successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				System.out.println("Connection closed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
