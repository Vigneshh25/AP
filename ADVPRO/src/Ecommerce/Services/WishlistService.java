package Ecommerce.Services;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class WishlistService {
    private static volatile WishlistService instance;
    private ConcurrentHashMap<String, CopyOnWriteArrayList<String>> userWishlists;

    private WishlistService() {
        userWishlists = new ConcurrentHashMap<>();
    }

    public static WishlistService getInstance() {
        if (instance == null) {
            synchronized (WishlistService.class) {
                if (instance == null) {
                    instance = new WishlistService();
                }
            }
        }
        return instance;
    }

    public void addToWishlist(String userId, String productId) {
        userWishlists.computeIfAbsent(userId, k -> new CopyOnWriteArrayList<>()).add(productId);
    }

    public List<String> getWishlist(String userId) {
        return userWishlists.getOrDefault(userId, new CopyOnWriteArrayList<>());
    }

    public void removeFromWishlist(String userId, String productId) {
        List<String> wishlist = userWishlists.get(userId);
        if (wishlist != null) {
            wishlist.remove(productId);
        }
    }
}
