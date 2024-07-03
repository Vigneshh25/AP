package Ecommerce.Entities;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private String userId;
    private Map<String, Integer> items; // productId -> quantity

    public Cart(String userId) {
        this.userId = userId;
        this.items = new HashMap<>();
    }

    public void addItem(String productId, int quantity) {
        items.put(productId, items.getOrDefault(productId, 0) + quantity);
    }

    public void removeItem(String productId) {
        items.remove(productId);
    }

    public Map<String, Integer> getItems() {
        return items;
    }
}
