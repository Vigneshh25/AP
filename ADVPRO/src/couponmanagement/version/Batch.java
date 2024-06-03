package couponmanagement.version;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Vignesh.V on 31/05/24.
 */ // Batch class
class Batch {
    private String batchId;
    private BatchType batchType;
    private BatchState batchState;
    private long validityPeriod;
    private String distributor;
    private CouponType couponType;
    private double worth;
    private int maxAllowedGrants;
    private Set<String> couponCodes;
    private AtomicInteger grantedCoupons;

    public Batch(String batchId, BatchType batchType, long validityPeriod, String distributor,
                 CouponType couponType, double worth, int maxAllowedGrants) {
        this.batchId = batchId;
        this.batchType = batchType;
        this.batchState = BatchState.CREATED;
        this.validityPeriod = validityPeriod;
        this.distributor = distributor;
        this.couponType = couponType;
        this.worth = worth;
        this.maxAllowedGrants = maxAllowedGrants;
        this.couponCodes = ConcurrentHashMap.newKeySet();
        this.grantedCoupons = new AtomicInteger(0);
    }

    // Getters and setters
    public String getBatchId() {
        return batchId;
    }

    public BatchType getBatchType() {
        return batchType;
    }

    public BatchState getBatchState() {
        return batchState;
    }

    public void setBatchState(BatchState batchState) {
        this.batchState = batchState;
    }

    public Set<String> getCouponCodes() {
        return couponCodes;
    }

    public int getGrantedCoupons() {
        return grantedCoupons.get();
    }

    public int getMaxAllowedGrants() {
        return maxAllowedGrants;
    }

    public void incrementGrantedCoupons() {
        this.grantedCoupons.incrementAndGet();
    }

    public long getValidityPeriod() {
        return validityPeriod;
    }

    public String getDistributor() {
        return distributor;
    }

    public CouponType getCouponType() {
        return couponType;
    }

    public double getWorth() {
        return worth;
    }
}
