package bikerental;

import java.util.Date;

class Rental {
    String rentalId;
    String productId;
    String customerId;
    Date rentalDate;
    Date returnDate;
    Date dueDate;

    public Rental(String rentalId, String productId, String customerId, Date rentalDate, Date dueDate) {
        this.rentalId = rentalId;
        this.productId = productId;
        this.customerId = customerId;
        this.rentalDate = rentalDate;
        this.dueDate = dueDate;
        this.returnDate = null;
    }

    public void returnProduct(Date returnDate) {
        this.returnDate = returnDate;
    }
}
