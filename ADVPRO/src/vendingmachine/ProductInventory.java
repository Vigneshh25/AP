package vendingmachine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vignesh.V on 14/06/24.
 */
public class ProductInventory {
    private static ProductInventory instance;
    private final Map<String, Product> products = new HashMap<>();

    private ProductInventory() {
    }

    public static synchronized ProductInventory getInstance() {
        if (instance == null) {
            instance = new ProductInventory();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.put(product.getProductCode(), product);
    }

    public Product getProducts(String code) {
        return products.get(code);
    }
    public Collection<Product> getProducts() {
        return products.values();
    }

    public boolean updateProduct(Product product) {
        return products.put(product.getProductCode(), product) != null;
    }

    public Map<String, Integer> getStock() {
        Map<String, Integer> stock = new HashMap<>();
        for (Product product : products.values()) {
            stock.put(product.getProductCode(), product.getQuantity());
        }
        return stock;
    }
}
