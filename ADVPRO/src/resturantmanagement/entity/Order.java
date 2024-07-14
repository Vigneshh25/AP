package resturantmanagement.entity;

import java.awt.*;
import java.util.Map;
import java.util.HashMap;

public class Order {
    private int orderId;
    private int tableId;
    private Map<MenuItem, Integer> items;
    private boolean isPrepared;

    public Order(int orderId, int tableId) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.items = new HashMap<>();
        this.isPrepared = false;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public Map<MenuItem, Integer> getItems() {
        return items;
    }

    public void addItem(MenuItem item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    public void markPrepared() {
        this.isPrepared = true;
    }

    public boolean isPrepared() {
        return isPrepared;
    }
}
