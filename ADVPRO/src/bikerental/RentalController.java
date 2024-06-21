package bikerental;

import java.util.Date;
import java.util.List;

/**
 * Created by Vignesh.V on 21/06/24.
 */ // RentalController Class
public class RentalController {
    private final ProductService productService = new ProductService();
    private final UserService userService = new UserService();

    public void rentProduct(String productId, String customerId, Date rentalDate, Date dueDate) {
        productService.rentProduct(productId, customerId, rentalDate, dueDate);
    }

    public void returnProduct(String rentalId, Date returnDate) {
        productService.returnProduct(rentalId, returnDate);
    }

    public List<Product> getAvailableProducts() {
        return productService.getAvailableProducts();
    }

    public void createCharge(String customerId, double amount, Date date) {
        productService.createCharge(customerId, amount, date);
    }

    public List<Rental> getOverdueRentals(Date currentDate) {
        return productService.getOverdueRentals(currentDate);
    }

    public List<Rental> getRentalsByCustomer(String customerId) {
        return productService.getRentalsByCustomer(customerId);
    }

    public double getCustomerBalance(String customerId) {
        return userService.getCustomerBalance(customerId);
    }

    public void addCustomer(Customer customer) {
        userService.addCustomer(customer);
    }
}
