package Couponmanagement;

import java.util.*;

public class Repository {
    private static Repository instance;
    private Map<String, User> users;
    private Map<String, Coupon> coupons;
    private Map<String, Voucher> vouchers;

    private Repository() {
        users = new HashMap<>();
        coupons = new HashMap<>();
        vouchers = new HashMap<>();
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public Map<String, Coupon> getCoupons() {
        return coupons;
    }

    public Map<String, Voucher> getVouchers() {
        return vouchers;
    }
}
