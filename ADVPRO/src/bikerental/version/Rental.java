package bikerental.version;

import java.time.LocalDate;

/**
 * Created by Vignesh.V on 05/06/24.
 */ // Rental class
class Rental {
    private String rentalId;
    private Reservation reservation;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double lateFee;

    public Rental(String rentalId, Reservation reservation, LocalDate dueDate) {
        this.rentalId = rentalId;
        this.reservation = reservation;
        this.dueDate = dueDate;
        this.lateFee = 0;
    }

    public void returnVehicle(LocalDate returnDate, LateFeeStrategy lateFeeStrategy) {
        this.returnDate = returnDate;
        if (returnDate.isAfter(dueDate)) {
            this.lateFee = lateFeeStrategy.calculateLateFee(dueDate, returnDate);
        }
    }

    // Additional methods as needed
}
