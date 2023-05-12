import java.util.*;
import java.sql.*;
public class SuperMarketApp {
    public static void main(String[] args) {
        Scanner sin = new Scanner(System.in);
        String role = "";
        ResultSet res;
        int choice;
        try {
            while (true) {
                System.out.print("\n\nEnter role: ");
                role = sin.nextLine();
                if (role.equals("admin")) {
                    Admin admin = new Admin();
                    int productId;
                    float price;
                    Boolean repeat = true;
                    while (repeat == true) {
                        System.out.println("\t\t\t=======================");
                        System.out.println("\t\t\t    \tMENU");
                        System.out.println("\t\t\t=======================\n");
                        System.out.println(
                                "\n\t\t\t1.Add new product\n\t\t\t2.Modify product price\n\t\t\t3.Delete product\n\t\t\t4.View products\n\t\t\t5.View Sales\n\t\t\t6.Logout\n\n");
                        System.out.print("\nEnter choice: ");
                        choice = Integer.parseInt(sin.nextLine());
                        switch (choice) {
                            case 1:
                                Product newProduct = new Product();
                                System.out.print("\nEnter product name: ");
                                newProduct.setName(sin.nextLine());
                                System.out.print("\nEnter product type: ");
                                newProduct.setType(sin.nextLine());
                                System.out.print("\nEnter product id: ");
                                newProduct.setId(Integer.parseInt(sin.nextLine()));
                                System.out.print("\nEnter product price: ");
                                newProduct.setPrice(Float.parseFloat(sin.nextLine()));
                                System.out.print("\nEnter stock quantity: ");
                                newProduct.setQuantity(Integer.parseInt(sin.nextLine()));
                                System.out.print("\nEnter manufactured date: ");
                                newProduct.setMfg(sin.nextLine());
                                System.out.print("\nEnter expiry date: ");
                                newProduct.setExp(sin.nextLine());
                                if (admin.addProduct(newProduct) != 0) {
                                    System.out.println("\nProduct added successfully..!");
                                }
                                break;
                            case 2:
                                System.out.print("\nEnter product id: ");
                                productId = Integer.parseInt(sin.nextLine());
                                System.out.print("\nEnter new price: ");
                                price = Float.parseFloat(sin.nextLine());
                                if (admin.updateProductPrice(productId, price) != 0) {
                                    System.out.println("\nProduct price updated successfully..!");
                                }
                                break;
                            case 3:
                                System.out.print("\nEnter product id: ");
                                productId = Integer.parseInt(sin.nextLine());
                                if (admin.deleteProduct(productId) != 0) {
                                    System.out.println("\nProduct deleted successfully..!");
                                }
                                break;
                            case 4:
                                res = Product.getAllProducts();
                                System.out.println(
                                    "\n===============================================================================================================================");
                                System.out.println(
                                        "Product ID      Product Name      Product price          Type         Mfg_date           Exp_date          Quantity");
                                        System.out.println(
                                            "===============================================================================================================================");
                                if (!res.next()) {
                                    System.out.println("No products found...!");
                                }
                                do{
                                    System.out.printf("\n%2s  %23s  %13s  %20s   %15s   %15s   %10s", res.getString(1),
                                            res.getString(2),
                                            res.getString(3), res.getString(4), res.getString(5), res.getString(6),
                                            res.getString(7));
                                }while(res.next());
                                System.out.println(
                                        "\n\n===============================================================================================================================\n");
                                break;
                            case 5:
                                String start = "", end = "";
                                System.out.print("\nEnter start date(YYYY-MM-DD): ");
                                start = sin.nextLine();
                                System.out.print("\nEnter end date(YYYY-MM-DD): ");
                                end = sin.nextLine();
                                res = admin.getOrderRecord(start, end);
                                if (!res.next()) {
                                    System.out.println("\nNo orders placed between " + start + " and " + end + "..!\n\n");
                                } else {
                                    System.out.println("\n==========================================================");
                                    System.out.println("Order ID      Item count      Total price         Date");
                                    System.out.println("==========================================================");
                                    do{
                                        System.out.printf("\n%2s  %15s  %18s  %17s", res.getString(1), res.getString(2),
                                                res.getString(3), res.getString(4));
                                    }while(res.next());
                                    System.out.println("\n\n==========================================================\n");
                                }
                                break;
                            case 6:
                                repeat = false;
                                break;
                        }
                    }
                } else if (role.equals("cashier")) {
                    Boolean repeat = true;
                    Cashier cashier = new Cashier();
                    while (repeat == true) {
                        System.out.println("\t\t\t=======================");
                        System.out.println("\t\t\t    \tMENU");
                        System.out.println("\t\t\t=======================\n");
                        System.out.println("\n\t\t\t1.Create order\n\t\t\t2.Logout\n\n");
                        System.out.print("\nEnter choice: ");
                        choice = Integer.parseInt(sin.nextLine());
                        switch (choice) {
                            case 1:
                                int product_id = -2;
                                int count = 0;
                                res = Product.getEligibleProducts();
                                System.out.println("\nEnter 0 to generate bill & -1 to cancel order");
                                System.out.println(
                                        "\n===============================================================================================================================");
                                System.out.println(
                                        "Product ID      Product Name      Product price          Type         Mfg_date           Exp_date          Quantity");
                                System.out.println(
                                        "===============================================================================================================================");
                                if (res == null) {
                                    System.out.println("\n\nNo products found...!");
                                }
                                while (res.next()) {
                                    if (res.getString(7) != "0") {
                                        System.out.printf("\n%2s  %23s  %13s  %20s   %15s   %15s   %10s",
                                                res.getString(1), res.getString(2),
                                                res.getString(3), res.getString(4), res.getString(5), res.getString(6),
                                                res.getString(7));
                                    }
                                }
                                System.out.println(
                                        "\n\n===============================================================================================================================\n");
                                ArrayList<LineItem> items = new ArrayList<LineItem>();
                                while (product_id != 0 && product_id != -1) {
                                    LineItem item = new LineItem();
                                    System.out.print("\n\nEnter product ID: ");

                                    product_id = Integer.parseInt(sin.nextLine());
                                    if(Product.isExpired(product_id)) {
                                        System.out.println("\nRequested product has expired..!\n");
                                    }
                                    else if (product_id != -1 && product_id != 0 && Product.isExist(product_id)) {
                                        while (true) {
                                            System.out.print("\n\nEnter count: ");
                                            count = Integer.parseInt(sin.nextLine());
                                            if (Product.isAvailable(product_id, count)) {
                                                item.setValue(product_id, count);
                                                items.add(item);
                                                break;
                                            } else {
                                                System.out.println("\nEnter feasible count...!");
                                            }
                                        }
                                    } else if (!Product.isExist(product_id) && product_id != 0) {
                                        System.out.println("\nProduct not available\n");
                                    }
                                }
                                if (product_id == 0 && product_id != -2) {
                                    Order order = cashier.addLineItem(items);
                                    cashier.generateBill(order);
                                    System.out.println("============================================================");
                                    System.out.println("Sno            Product Name      Quantity           Price");
                                    System.out.println("============================================================");
                                    for (int i = 0; i < order.getItems().size(); i++) {
                                        System.out.printf("\n%2s  %23s   %10s  %13s  ",
                                                i + 1, order.getItems().get(i).getName(),order.getItems().get(i).getCount(),
                                                order.getItems().get(i).getPrice());
                                    }
                                    System.out.println("\n\n============================================================");
                                    System.out.println("                           Total price  =          " + order.getTotalPrice());
                                    System.out.println("============================================================\n");
                                }
                                if (product_id == -1) {
                                    System.out.println("\n\nOrder cancelled...!");
                                }
                                break;
                            case 2:
                                repeat = false;
                                break;
                        }
                    }
                }
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        sin.close();
    }
}
