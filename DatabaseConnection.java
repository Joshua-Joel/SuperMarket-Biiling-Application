import java.sql.*;
class DatabaseConnection {
    public static Connection getDbConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SuperMarket", "root", "");
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return connection;
    }
}
