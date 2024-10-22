package CRUD;

public class Test {
    public static void main(String[] args) {
        DatabaseConnection.getConnection();
        DatabaseConnection.closeConnection();
    }
}

