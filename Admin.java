import java.sql.*;
public class Admin {
    int addProduct(Product product) {
        try {
            Statement statement = DatabaseConnection.getDbConnection().createStatement();
            return statement.executeUpdate("insert into Product values(" + product.getId() + ",'" + product.getName()
                    + "'," + product.getPrice() + ",'" + product.getType() + "','" + product.getMfg() + "','"
                    + product.getExp() + "'," + product.getQuantity() + ");");
        } catch (Exception exception) {
            System.out.println(exception);
            return 0;
        }
    }

    int updateProductPrice(int id, float price) {
        try {
            Statement statement = DatabaseConnection.getDbConnection().createStatement();
            return statement.executeUpdate(
                    "update product set price=" + price + "where id=" + id + ";");
        } catch (Exception exception) {
            System.out.println(exception);
            return 0;
        }
    }

    int deleteProduct(int id) {
        try {
            Statement statement = DatabaseConnection.getDbConnection().createStatement();
            return statement.executeUpdate("delete from product where id=" + id + ";");
        } catch (Exception exception) {
            System.out.println(exception);
            return 0;
        }
    }

    ResultSet getOrderRecord(String start, String end) {
        try {
            Statement statement = DatabaseConnection.getDbConnection().createStatement();
            ResultSet resultSet = statement
                    .executeQuery("select * from Orders where date>='" + start + "' and date<='" + end + "'");
            return resultSet;
        } catch (Exception exception) {
            System.out.println(exception);
            return null;
        }
    }
}
