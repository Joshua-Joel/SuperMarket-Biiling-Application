import java.sql.*;
public class Product {
    private int id;
    private String name;
    private float price;
    private String type;
    private String mfg;
    private String exp;
    private int quantity;

    int getId() {
        return this.id;
    }

    String getName() {
        return this.name;
    }

    float getPrice() {
        return this.price;
    }

    int getQuantity() {
        return this.quantity;
    }

    String getMfg() {
        return this.mfg;
    }

    String getExp() {
        return this.exp;
    }

    String getType() {
        return this.type;
    }

    void setName(String name) {
        this.name = name;
    }

    void setPrice(float price) {
        this.price = price;
    }

    void setId(int id) {
        this.id = id;
    }

    void setMfg(String mfg) {
        this.mfg = mfg;
    }

    void setExp(String exp) {
        this.exp = exp;
    }

    void setType(String type) {
        this.type = type;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    static Boolean isExist(int id) {
        try {
            Statement statement = DatabaseConnection.getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select quantity from Product where id=" + id);
            if (resultSet.next() && resultSet.getString(1) != "0") {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
    }
    static Boolean isExpired(int id){
        try {
            Statement statement = DatabaseConnection.getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select id from Product where id=" + id+" and exp_date>=current_date()");
            if (!resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
    }
    static Boolean isAvailable(int id, int count) {
        try {
            Statement statement = DatabaseConnection.getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select quantity from Product where id=" + id);
            if (resultSet.next() && Integer.parseInt(resultSet.getString(1)) >= count) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
    }

    static ResultSet getAllProducts() {
        try {
            Statement statement = DatabaseConnection.getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Product");
            return resultSet;
        } catch (Exception exception) {
            System.out.println(exception);
            return null;
        }
    }

    static ResultSet getEligibleProducts() {
        try {
            Statement statement = DatabaseConnection.getDbConnection().createStatement();
            ResultSet resultSet = statement
                    .executeQuery("select * from Product where quantity !=0 and exp_date > current_date()");
            return resultSet;
        } catch (Exception exception) {
            System.out.println(exception);
            return null;
        }
    }
}
