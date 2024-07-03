package Ecommerce.Services;

import Ecommerce.Entities.Cart;

import java.util.HashMap;
import java.util.Map;

public class CartService {
    private static CartService instance;
    private Map<String, Cart> userCarts;

    private CartService() {
        userCarts = new HashMap<>();
    }

    public static CartService getInstance() {
        if (instance == null) {
            instance = new CartService();
        }
        return instance;
    }

    public Cart getCartByUserId(String userId) {
        return userCarts.computeIfAbsent(userId, k -> new Cart(userId));
    }

    public void addItemToCart(String userId, String productId, int quantity) {
        Cart cart = getCartByUserId(userId);
        cart.addItem(productId, quantity);
    }

    public void removeItemFromCart(String userId, String productId) {
        Cart cart = getCartByUserId(userId);
        cart.removeItem(productId);
    }
}
