package CRUD;

import java.sql.*;

public class CRUDExample {

	public static void insertStudent(String name, int age, String email) {
		Connection connection = DatabaseConnection.getConnection();
		String query = "INSERT INTO students (name, age, email) VALUES (?, ?, ?)";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setString(3, email);

			int rowsAffected = pstmt.executeUpdate();
			System.out.println(rowsAffected + " row(s) inserted.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeConnection();
		}
	}

	public static void selectStudents() {
		Connection connection = DatabaseConnection.getConnection();
		String query = "SELECT * FROM students";

		try (Statement stmt = connection.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");

				System.out.println(id + ": " + name + ", " + age + ", " + email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeConnection();
		}
	}

	public static void updateStudent(int id, String newName, int newAge, String newEmail) {
		Connection connection = DatabaseConnection.getConnection();
		String query = "UPDATE students SET name = ?, age = ?, email = ? WHERE id = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, newName);
			pstmt.setInt(2, newAge);
			pstmt.setString(3, newEmail);
			pstmt.setInt(4, id);

			int rowsAffected = pstmt.executeUpdate();
			System.out.println(rowsAffected + " row(s) updated.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeConnection();
		}
	}

	public static void deleteStudent(int id) {
		Connection connection = DatabaseConnection.getConnection();
		String query = "DELETE FROM students WHERE id = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setInt(1, id);

			int rowsAffected = pstmt.executeUpdate();
			System.out.println(rowsAffected + " row(s) deleted.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeConnection();
		}
	}

	public static void main(String[] args) {
//		insertStudent("Hohn Joe", 20, "hohn.joe@example.com");
		updateStudent(5, "Kon Moe", 10, "kon.moe@gmail.com");
		selectStudents();
	}
}
