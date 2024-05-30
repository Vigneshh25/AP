package bikerental.model;

import java.util.Date;

public class Rental {
    public String rentalId;
    public String productId;
    public String customerId;
    Date rentalDate;
    public Date returnDate;
    public Date dueDate;

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
