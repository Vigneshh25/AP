package couponmanagement.version;

/**
 * Created by Vignesh.V on 31/05/24.
 */ // Enums for BatchState, BatchType, and CouponType
enum BatchState {
    CREATED, APPROVED, ACTIVE, EXPIRED, SUSPENDED, TERMINATED
}
enum BatchType {
    OPEN, CLOSED
}
enum CouponType {
    GENERIC, UNIQUE
}
