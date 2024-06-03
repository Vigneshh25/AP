package couponmanagement.version;

import java.util.*;

// Custom exceptions


// Main class for demonstration
public class CouponManagementSystemApp {
    public static void main(String[] args) {
        CouponManagementSystem cms = new CouponManagementSystem();

        // Create a batch
        Batch batch = new Batch("batch1", BatchType.OPEN, System.currentTimeMillis() + 1000000, 
                                "Distributor1", CouponType.GENERIC, 10.0, 100);
        cms.createBatch(batch);

        // Update batch state to APPROVED
        cms.updateState(batch.getBatchId(), BatchState.APPROVED);

        // Update batch state to ACTIVE
        cms.updateState(batch.getBatchId(), BatchState.ACTIVE);

        // Ingest coupons
        Set<String> coupons = new HashSet<>(Arrays.asList("coupon1", "coupon2", "coupon3"));
        cms.ingestCoupons(batch.getBatchId(), coupons);

        // Grant a coupon
        Coupon grantedCoupon = cms.grantCoupon(batch.getBatchId());
        System.out.println("Granted Coupon: " + grantedCoupon.getCouponId());

        // Get coupon count
        int couponCount = cms.getCouponsCount(batch.getBatchId());
        System.out.println("Remaining Coupons Count: " + couponCount);
    }
}
