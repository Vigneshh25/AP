package Ecommerce.Services;

import Ecommerce.Entities.Product;

import java.util.List;
import java.util.stream.Collectors;

public class SearchService {
    private static volatile SearchService instance;
    private ProductService productService;

    private SearchService() {
        productService = ProductService.getInstance();
    }

    public static SearchService getInstance() {
        if (instance == null) {
            synchronized (SearchService.class) {
                if (instance == null) {
                    instance = new SearchService();
                }
            }
        }
        return instance;
    }

    public List<Product> searchProducts(String query) {
        return productService.getAllProducts().stream()
                .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
