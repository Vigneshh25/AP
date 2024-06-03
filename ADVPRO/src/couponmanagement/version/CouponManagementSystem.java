package couponmanagement.version;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Vignesh.V on 31/05/24.
 */ // CouponManagementSystem class
class CouponManagementSystem {
    private Map<String, Batch> batches;
    private Map<String, Coupon> coupons;

    public CouponManagementSystem() {
        this.batches = new ConcurrentHashMap<>();
        this.coupons = new ConcurrentHashMap<>();
    }

    public void createBatch(Batch batch) {
        if (batches.containsKey(batch.getBatchId())) {
            throw new InvalidRequestException("Batch with the same ID already exists.");
        }
        batches.put(batch.getBatchId(), batch);
    }

    public void updateState(String batchId, BatchState newState) {
        Batch batch = batches.get(batchId);
        if (batch == null) {
            throw new BatchNotFoundException("Batch not found.");
        }
        if (isValidTransition(batch.getBatchState(), newState)) {
            batch.setBatchState(newState);
        } else {
            throw new InvalidTransitionException("Invalid state transition.");
        }
    }

    public Batch getBatch(String batchId) {
        Batch batch = batches.get(batchId);
        if (batch == null) {
            throw new BatchNotFoundException("Batch not found.");
        }
        return batch;
    }

    public void ingestCoupons(String batchId, Set<String> couponCodes) {
        Batch batch = batches.get(batchId);
        if (batch == null) {
            throw new BatchNotFoundException("Batch not found.");
        }
        batch.getCouponCodes().addAll(couponCodes);
    }

    public Coupon grantCoupon(String batchId) {
        Batch batch = batches.get(batchId);
        if (batch == null) {
            throw new BatchNotFoundException("Batch not found.");
        }
        if (batch.getBatchState() != BatchState.ACTIVE) {
            throw new BatchNotActiveException("Batch is not active.");
        }
        if (batch.getGrantedCoupons() >= batch.getMaxAllowedGrants() || batch.getCouponCodes().isEmpty()) {
            throw new BatchExhaustedException("Batch is exhausted.");
        }
        String couponCode = batch.getCouponCodes().iterator().next();
        batch.getCouponCodes().remove(couponCode);
        batch.incrementGrantedCoupons();
        Coupon coupon = new Coupon(couponCode, batchId, batch.getWorth());
        coupons.put(coupon.getCouponId(), coupon);
        return coupon;
    }

    public Coupon getCoupon(String couponId) {
        Coupon coupon = coupons.get(couponId);
        if (coupon == null) {
            throw new CouponNotFoundException("Coupon not found.");
        }
        return coupon;
    }

    public int getCouponsCount(String batchId) {
        Batch batch = batches.get(batchId);
        if (batch == null) {
            throw new BatchNotFoundException("Batch not found.");
        }
        return batch.getCouponCodes().size();
    }

    private boolean isValidTransition(BatchState currentState, BatchState newState) {
        switch (currentState) {
            case CREATED:
                return newState == BatchState.APPROVED;
            case APPROVED:
                return newState == BatchState.ACTIVE;
            case ACTIVE:
                return newState == BatchState.EXPIRED || newState == BatchState.SUSPENDED || newState == BatchState.TERMINATED;
            case SUSPENDED:
                return newState == BatchState.ACTIVE;
            case EXPIRED:
            case TERMINATED:
                return false;
            default:
                return false;
        }
    }
}
