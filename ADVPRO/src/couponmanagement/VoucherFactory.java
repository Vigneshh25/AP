package couponmanagement;

import java.util.UUID;

public class VoucherFactory {
    public static Voucher createVoucher(VoucherType type, String couponId, String assignedUserId) {
        return new Voucher(UUID.randomUUID().toString(), type, couponId, assignedUserId);
    }
}
