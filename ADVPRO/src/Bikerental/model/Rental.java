package Bikerental.model;

import java.util.Date;

/**
 * Created by Vignesh.V on 21/06/24.
 */ // Rental Class
public class Rental {
    private String rentalId;
    private String productId;
    private String customerId;
    private Date rentalDate;
    private Date dueDate;
    private Date returnDate;

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

    public String getRentalId() {
        return rentalId;
    }

    public String getProductId() {
        return productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
