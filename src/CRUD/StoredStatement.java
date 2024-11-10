package CRUD;

import java.sql.*;

public class StoredStatement {

    public static void activeEmployee() {
        String callQuery = "SELECT * FROM activeemployeedetails";  // Ensure the view name is correct
        Connection con = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(callQuery);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                Double salary = rs.getDouble("salary");

                System.out.println(
                        "ID: " + id + ", Name: " + name + ", Department: " + department + ", Salary: " + salary);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        StoredStatement.activeEmployee();
    }
}
