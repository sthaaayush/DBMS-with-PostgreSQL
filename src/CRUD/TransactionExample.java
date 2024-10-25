package CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionExample {
	public static void main(String[] args) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement debitStm = null;
		PreparedStatement creditStm = null;
		PreparedStatement dis = null;

		try {
			conn.setAutoCommit(false);

			String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
			String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
			String displayQuery = "SELECT * FROM accounts";

			debitStm = conn.prepareStatement(debitQuery);
			creditStm = conn.prepareStatement(creditQuery);
			dis = conn.prepareStatement(displayQuery);

			// Debit from account 1
			debitStm.setDouble(1, 20.00);
			debitStm.setInt(2, 1);
			debitStm.executeUpdate();

			// Credit to account 2
			creditStm.setDouble(1, 20.00);
			creditStm.setInt(2, 2);
			creditStm.executeUpdate();

			// Credit to account 3
			creditStm.setDouble(1, 10.00);
			creditStm.setInt(2, 3);
			creditStm.executeUpdate();

			conn.commit();
			System.out.println("Transaction successful!");

			// Display updated account balances
			ResultSet rs = dis.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("account_id") + " | " + rs.getString("account_holder") + " | "
						+ rs.getDouble("balance"));
			}
		} catch (Exception e) {
			if (conn != null) {
				conn.rollback();
			}
			System.out.println("Transaction failed, changes rolled back.");
			e.printStackTrace();
		} finally {
			if (debitStm != null)
				debitStm.close();
			if (creditStm != null)
				creditStm.close();
			if (dis != null)
				dis.close();
			DatabaseConnection.closeConnection();
		}
	}
}
