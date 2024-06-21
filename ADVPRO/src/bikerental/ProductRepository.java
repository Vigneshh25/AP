package bikerental;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Vignesh.V on 21/06/24.
 */ // ProductRepository Singleton Class
class ProductRepository {
    private static ProductRepository productRepository;
    private Map<String, Product> products = new HashMap<>();
    private Map<String, Rental> rentals = new HashMap<>();
    private Map<String, Charge> charges = new HashMap<>();

    private ProductRepository() {
    }

    public static synchronized ProductRepository getInstance() {
        if (productRepository == null) {
            productRepository = new ProductRepository();
        }
        return productRepository;
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public void removeProduct(String productId) {
        products.remove(productId);
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    public List<Product> getAvailableProducts() {
        return products.values().stream().filter(Product::isAvailable).collect(Collectors.toList());
    }

    public void addRental(Rental rental) {
        rentals.put(rental.getRentalId(), rental);
    }

    public Rental getRental(String rentalId) {
        return rentals.get(rentalId);
    }

    public List<Rental> getOverdueRentals(Date currentDate) {
        return rentals.values().stream().filter(r -> r.getReturnDate() == null && r.getDueDate().before(currentDate)).collect(Collectors.toList());
    }

    public List<Rental> getRentalsByCustomer(String customerId) {
        return rentals.values().stream().filter(r -> r.getCustomerId().equals(customerId)).collect(Collectors.toList());
    }

    public void addCharge(Charge charge) {
        charges.put(charge.getChargeId(), charge);
    }
}
