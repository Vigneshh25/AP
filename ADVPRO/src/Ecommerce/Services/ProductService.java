package Ecommerce.Services;

import Ecommerce.Entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static ProductService instance;
    private List<Product> products;

    private ProductService() {
        products = new ArrayList<>();
    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return products;
    }


    public Product getProductById(String key) {
        return products.stream()
                .filter(product -> product.getId().equals(key))
                .findFirst()
                .orElse(null); // Or handle the case where the product is not found
    }

}
