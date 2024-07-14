package resturantmanagement.entity;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private int tableId;
    private int capacity;
    private List<Order> orders;

    public Table(int tableId, int capacity) {
        this.tableId = tableId;
        this.capacity = capacity;
        this.orders = new ArrayList<>();
    }

    public int getTableId() {
        return tableId;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }
}
