package bikerental.version;

import java.time.LocalDate;

/**
 * Created by Vignesh.V on 05/06/24.
 */
class DefaultLateFeeStrategy implements LateFeeStrategy {
    private static final double DAILY_LATE_FEE = 50.0;

    @Override
    public double calculateLateFee(LocalDate dueDate, LocalDate returnDate) {
        long daysLate = java.time.temporal.ChronoUnit.DAYS.between(dueDate, returnDate);
        return daysLate * DAILY_LATE_FEE;
    }
}
