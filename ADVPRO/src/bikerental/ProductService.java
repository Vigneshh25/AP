package bikerental;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Vignesh.V on 21/06/24.
 */ // ProductService Class
public class ProductService {
    private final ProductRepository productRepository = ProductRepository.getInstance();

    public void rentProduct(String productId, String customerId, Date rentalDate, Date dueDate) {
        Product product = productRepository.getProduct(productId);
        if (product != null && product.isAvailable()) {
            product.rent();
            String rentalId = UUID.randomUUID().toString();
            Rental rental = new Rental(rentalId, productId, customerId, rentalDate, dueDate);
            productRepository.addRental(rental);
        }
    }

    public void returnProduct(String rentalId, Date returnDate) {
        Rental rental = productRepository.getRental(rentalId);
        if (rental != null) {
            rental.returnProduct(returnDate);
            Product product = productRepository.getProduct(rental.getProductId());
            if (product != null) {
                product.returnProduct();
            }
        }
    }

    public List<Product> getAvailableProducts() {
        return productRepository.getAvailableProducts();
    }

    public void createCharge(String customerId, double amount, Date date) {
        Customer customer = UserRepository.getInstance().getCustomer(customerId);
        if (customer != null) {
            customer.addCharge(amount);
            String chargeId = UUID.randomUUID().toString();
            Charge charge = new Charge(chargeId, customerId, amount, date);
            productRepository.addCharge(charge);
        }
    }

    public List<Rental> getOverdueRentals(Date currentDate) {
        return productRepository.getOverdueRentals(currentDate);
    }

    public List<Rental> getRentalsByCustomer(String customerId) {
        return productRepository.getRentalsByCustomer(customerId);
    }
}
