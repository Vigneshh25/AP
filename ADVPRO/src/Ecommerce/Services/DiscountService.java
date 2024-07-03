package Ecommerce.Services;

import Ecommerce.Entities.Discount;

import java.util.concurrent.ConcurrentHashMap;

public class DiscountService {
    private static volatile DiscountService instance;
    private ConcurrentHashMap<String, Discount> discounts;

    private DiscountService() {
        discounts = new ConcurrentHashMap<>();
    }

    public static DiscountService getInstance() {
        if (instance == null) {
            synchronized (DiscountService.class) {
                if (instance == null) {
                    instance = new DiscountService();
                }
            }
        }
        return instance;
    }

    public void addDiscount(Discount discount) {
        discounts.put(discount.getCode(), discount);
    }

    public Discount getDiscount(String code) {
        return discounts.get(code);
    }
}
