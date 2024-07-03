package Ecommerce.Services;

import Ecommerce.Entities.Product;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class RecommendationService {
    private static volatile RecommendationService instance;
    private ConcurrentHashMap<String, List<String>> userRecommendations;

    private RecommendationService() {
        userRecommendations = new ConcurrentHashMap<>();
    }

    public static RecommendationService getInstance() {
        if (instance == null) {
            synchronized (RecommendationService.class) {
                if (instance == null) {
                    instance = new RecommendationService();
                }
            }
        }
        return instance;
    }

    public void addRecommendation(String userId, List<String> recommendedProductIds) {
        userRecommendations.put(userId, recommendedProductIds);
    }

    public List<Product> getRecommendations(String userId, ProductService productService) {
        List<String> recommendedProductIds = userRecommendations.getOrDefault(userId, Collections.emptyList());
        return recommendedProductIds.stream()
                .map(productService::getProductById)
                .collect(Collectors.toList());
    }
}
