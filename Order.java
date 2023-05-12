import java.util.*;
public class Order {
    private ArrayList<LineItem> items = new ArrayList<LineItem>();

    ArrayList<LineItem> getItems() {
        return this.items;
    }

    float getTotalPrice() {
        int total = 0;
        for (int i = 0; i < this.items.size(); i++) {
            total += this.items.get(i).getPrice();
        }
        return total;
    }

    void addItem(LineItem item) {
        this.items.add(item);
    }
}
