package bikerental;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Vignesh.V on 21/06/24.
 */ // ProductService Class
public class ProductService {
    private final ProductRepository productRepository = ProductRepository.getInstance();

    public void rentProduct(String productId, String customerId, Date rentalDate, Date dueDate) throws RentalException {
        Product product = productRepository.getProduct(productId);
        if (product == null || !product.isAvailable()) {
            throw new RentalException("Product not available for rental.");
        }
        Date currentDate = new Date();
        if (rentalDate.after(currentDate)) {
            throw new RentalException("Rental date cannot be in the future.");
        }
        if (dueDate.before(rentalDate)) {
            throw new RentalException("Due date must be after rental date.");
        }

        product.rent();
        String rentalId = UUID.randomUUID().toString();
        Rental rental = new Rental(rentalId, productId, customerId, rentalDate, dueDate);
        productRepository.addRental(rental);
    }

    public void returnProduct(String rentalId, Date returnDate) throws RentalException {
        Rental rental = productRepository.getRental(rentalId);
        if (rental == null) {
            throw new RentalException("Rental not found.");
        }
        if (rental.getReturnDate() != null) {
            throw new RentalException("Product already returned.");
        }
        if (returnDate.before(rental.getDueDate())) {
            throw new RentalException("Return date is before due date.");
        }

        rental.returnProduct(returnDate);
        Product product = productRepository.getProduct(rental.getProductId());
        if (product != null) {
            product.returnProduct();
        }

        // Calculate late fee if applicable
        long daysLate = (returnDate.getTime() - rental.getDueDate().getTime()) / (1000 * 60 * 60 * 24);
        if (daysLate > 0) {
            double lateFee = daysLate * 5.0; // Example: $5 per day late fee
            String chargeId = UUID.randomUUID().toString();
            Charge charge = new Charge(chargeId, rental.getCustomerId(), lateFee, returnDate);
            productRepository.addCharge(charge);
        }
    }

    public List<Product> getAvailableProducts() {
        return productRepository.getAvailableProducts();
    }

    public void createCharge(String customerId, double amount, Date date) throws ChargeException {
        if (amount <= 0) {
            throw new ChargeException("Amount must be greater than zero.");
        }
        Customer customer = UserRepository.getInstance().getCustomer(customerId);
        if (customer == null) {
            throw new ChargeException("Customer not found.");
        }

        customer.addCharge(amount);
        String chargeId = UUID.randomUUID().toString();
        Charge charge = new Charge(chargeId, customerId, amount, date);
        productRepository.addCharge(charge);
    }

    public List<Rental> getOverdueRentals(Date currentDate) {
        return productRepository.getOverdueRentals(currentDate);
    }

    public List<Rental> getRentalsByCustomer(String customerId) {
        return productRepository.getRentalsByCustomer(customerId);
    }
}
