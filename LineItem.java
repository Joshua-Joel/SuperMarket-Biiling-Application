import java.sql.*;
public class LineItem {
    private int id;
    private int count;
    private String name;
    private float price;

    void setValue(int id, int count) {
        this.id = id;
        this.count = count;
        try {
            Statement statement = DatabaseConnection.getDbConnection().createStatement();
            ResultSet res = statement.executeQuery("select price from product where id=" + id);
            res.next();
            this.price = Float.parseFloat(res.getString(1)) * this.count;
            res = statement.executeQuery("select name from product where id=" + this.id);
            res.next();
            this.name = res.getString(1);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    int getId() {
        return this.id;
    }

    int getCount() {
        return this.count;
    }

    float getPrice() {
        return this.price;
    }

    String getName() {
        return this.name;
    }
}
