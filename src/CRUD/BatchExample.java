package CRUD;

import java.sql.*;

public class BatchExample {
	public static void insertStudentBatch(String[][] students) {
		String query = "INSERT INTO students  (name, age, email) VALUES (?, ?, ?)";
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(query);
			conn.setAutoCommit(false);

			for (String[] details : students) {
				stm.setString(1, details[0]);
				stm.setInt(2, Integer.parseInt(details[1]));
				stm.setString(3, details[2]);
				stm.addBatch();
			}

			int[] rowAffected = stm.executeBatch();
			conn.commit();

			System.out.println(rowAffected.length + " row affected");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeConnection(conn, stm);;
		}
	}

	public static void main(String[] args) {
		String[][] students = { { "Hice", "22", "hice@gmail.com" }, { "Mob", "23", "mob@gmail.com" },
				{ "marlie", "21", "marlie@gmail.com" } };

		BatchExample.insertStudentBatch(students);

		CRUDExample.selectStudents();
	}

}
