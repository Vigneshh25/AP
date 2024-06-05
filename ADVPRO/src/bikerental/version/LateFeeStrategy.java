package bikerental.version;

import java.time.LocalDate;

/**
 * Created by Vignesh.V on 05/06/24.
 */ // Late Fee Strategy
interface LateFeeStrategy {
    double calculateLateFee(LocalDate dueDate, LocalDate returnDate);
}
