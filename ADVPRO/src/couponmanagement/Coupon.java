package couponmanagement;

import java.util.Date;

public class Coupon {
    private String couponId;
    private String description;
    private Rule rule;
    private int overallUsageLimit;
    private int perUserUsageLimit;
    private Date expiryDate;
    private boolean isActive;
    private int currentUsageCount;

    public Coupon(String couponId, String description, Rule rule, int overallUsageLimit, int perUserUsageLimit, Date expiryDate, boolean isActive) {
        this.couponId = couponId;
        this.description = description;
        this.rule = rule;
        this.overallUsageLimit = overallUsageLimit;
        this.perUserUsageLimit = perUserUsageLimit;
        this.expiryDate = expiryDate;
        this.isActive = isActive;
        this.currentUsageCount = 0;
    }

    public String getCouponId() {
        return couponId;
    }

    public String getDescription() {
        return description;
    }

    public Rule getRule() {
        return rule;
    }

    public int getOverallUsageLimit() {
        return overallUsageLimit;
    }

    public int getPerUserUsageLimit() {
        return perUserUsageLimit;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getCurrentUsageCount() {
        return currentUsageCount;
    }

    public void incrementUsageCount() {
        this.currentUsageCount++;
    }
}
