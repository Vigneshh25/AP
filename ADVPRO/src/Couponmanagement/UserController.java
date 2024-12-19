package Couponmanagement;

import java.util.*;

public class UserController {
    private Repository repository = Repository.getInstance();

    public List<Coupon> viewAvailableCoupons(String userId) {
        User user = repository.getUsers().get(userId);
        List<Coupon> availableCoupons = new ArrayList<>();
        for (Coupon coupon : repository.getCoupons().values()) {
            if (coupon.isActive() && coupon.getExpiryDate().after(new Date()) && coupon.getCurrentUsageCount() < coupon.getOverallUsageLimit()) {
                availableCoupons.add(coupon);
            }
        }
        return availableCoupons;
    }

    public List<Voucher> viewAvailableVouchers(String userId) {
        User user = repository.getUsers().get(userId);
        List<Voucher> availableVouchers = new ArrayList<>();
        for (Voucher voucher : repository.getVouchers().values()) {
            if (!voucher.isUsed() && (voucher.getType() == VoucherType.UNASSIGNED || (voucher.getType() == VoucherType.PREASSIGNED && voucher.getAssignedUserId().equals(userId)))) {
                availableVouchers.add(voucher);
            }
        }
        return availableVouchers;
    }

    public String useVoucher(String userId, String voucherId, double cartValue) {
        Voucher voucher = repository.getVouchers().get(voucherId);
        if (voucher != null && !voucher.isUsed()) {
            Coupon coupon = repository.getCoupons().get(voucher.getCouponId());
            if (coupon != null && coupon.isActive() && coupon.getExpiryDate().after(new Date())) {
                User user = repository.getUsers().get(userId);
                if (coupon.getRule().evaluate(user, cartValue)) {
                    voucher.useVoucher();
                    coupon.incrementUsageCount();
                    return "Voucher used successfully. Discount: " + coupon.getDescription();
                }
            }
        }
        return "Failed to use voucher.";
    }

    // Additional user methods...
}
