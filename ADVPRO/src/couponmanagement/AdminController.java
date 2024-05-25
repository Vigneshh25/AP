package couponmanagement;

import java.util.*;

public class AdminController {
    private Repository repository = Repository.getInstance();

    public String createCoupon(String description, String ruleCondition, int overallUsageLimit, int perUserUsageLimit, Date expiryDate, boolean isActive) {
        String couponId = UUID.randomUUID().toString();
        Rule rule = RuleFactory.createRule(ruleCondition);
        Coupon coupon = new Coupon(couponId, description, rule, overallUsageLimit, perUserUsageLimit, expiryDate, isActive);
        repository.getCoupons().put(couponId, coupon);
        return couponId;
    }

    public void activateCoupon(String couponId, boolean isActive) {
        Coupon coupon = repository.getCoupons().get(couponId);
        if (coupon != null) {
            coupon.setActive(isActive);
        }
    }

    public void deleteCoupon(String couponId) {
        repository.getCoupons().remove(couponId);
    }

    // Additional admin methods...
}
