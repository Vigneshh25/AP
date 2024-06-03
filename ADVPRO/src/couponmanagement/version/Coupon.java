package couponmanagement.version;

/**
 * Created by Vignesh.V on 31/05/24.
 */ // Coupon class
class Coupon {
    private String couponId;
    private String batchId;
    private double worth;

    public Coupon(String couponId, String batchId, double worth) {
        this.couponId = couponId;
        this.batchId = batchId;
        this.worth = worth;
    }

    // Getters
    public String getCouponId() {
        return couponId;
    }

    public String getBatchId() {
        return batchId;
    }

    public double getWorth() {
        return worth;
    }
}
