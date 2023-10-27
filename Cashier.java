import java.util.*;
import java.sql.*;
//changes made
public class Cashier {
    Order addLineItem(ArrayList<LineItem> items) {
        Order o1 = new Order();
        for (int i = 0; i < items.size(); i++) {
            o1.addItem(items.get(i));
        }
        return o1;
    }

    void generateBill(Order o) {
        try {
            ResultSet res;
            Statement statement = DatabaseConnection.getDbConnection().createStatement();
            res = statement.executeQuery("Select max(ID) as maxID from orders;");
            int orderID = 0;
            if (res.next()) {
                orderID = Integer.parseInt(res.getString("maxID")) + 1;
            } else {
                orderID = 1;
            }
            statement.executeUpdate("insert into orders values(" + orderID + "," + o.getItems().size() + ","
                    + o.getTotalPrice() + ",current_date());");
            for (int i = 0; i < o.getItems().size(); i++) {
                statement.executeUpdate(
                        "insert into order_product values(" + orderID + "," + o.getItems().get(i).getId() + ");");
                statement.executeUpdate("update product set quantity = quantity-" + o.getItems().get(i).getCount()
                        + " where id=" + o.getItems().get(i).getId());
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
