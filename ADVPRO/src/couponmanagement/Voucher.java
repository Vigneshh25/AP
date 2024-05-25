package couponmanagement;

public class Voucher {
    private String voucherId;
    private VoucherType type;
    private String couponId;
    private String assignedUserId;
    private boolean isUsed;

    public Voucher(String voucherId, VoucherType type, String couponId, String assignedUserId) {
        this.voucherId = voucherId;
        this.type = type;
        this.couponId = couponId;
        this.assignedUserId = assignedUserId;
        this.isUsed = false;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public VoucherType getType() {
        return type;
    }

    public String getCouponId() {
        return couponId;
    }

    public String getAssignedUserId() {
        return assignedUserId;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void useVoucher() {
        this.isUsed = true;
    }
}
