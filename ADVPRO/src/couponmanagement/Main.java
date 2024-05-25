package couponmanagement;

import java.util.Date;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        AdminController adminController = new AdminController();
        UserController userController = new UserController();
        Repository repository = Repository.getInstance();

        // Creating Users
        User user1 = new User(UUID.randomUUID().toString(), "Alice", 25);
        User user2 = new User(UUID.randomUUID().toString(), "Bob", 17);
        repository.getUsers().put(user1.getUserId(), user1);
        repository.getUsers().put(user2.getUserId(), user2);

        // Admin creating a coupon
        String couponId = adminController.createCoupon(
            "10% off for orders above $1000",
            "age > 18 && cartValue > 1000",
            100, 1, new Date(System.currentTimeMillis() + 24*60*60*1000), true
        );

        // Admin viewing and activating/deactivating coupons
        adminController.activateCoupon(couponId, true);

        // Users viewing available coupons and vouchers
        System.out.println(userController.viewAvailableCoupons(user1.getUserId()));
        System.out.println(userController.viewAvailableCoupons(user2.getUserId()));

        // Admin creating vouchers
        Voucher voucher1 = VoucherFactory.createVoucher(VoucherType.UNASSIGNED, couponId, null);
        Voucher voucher2 = VoucherFactory.createVoucher(VoucherType.PREASSIGNED, couponId, user1.getUserId());
        repository.getVouchers().put(voucher1.getVoucherId(), voucher1);
        repository.getVouchers().put(voucher2.getVoucherId(), voucher2);

        // Users viewing available vouchers
        System.out.println(userController.viewAvailableVouchers(user1.getUserId()));
        System.out.println(userController.viewAvailableVouchers(user2.getUserId()));

        // User using a voucher
        System.out.println(userController.useVoucher(user1.getUserId(), voucher2.getVoucherId(), 1200));
        System.out.println(userController.useVoucher(user2.getUserId(), voucher1.getVoucherId(), 1500));
    }
}
