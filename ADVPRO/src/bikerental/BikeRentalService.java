package bikerental;

import bikerental.model.*;

import java.util.*;

class BikeRentalService {
    Inventory inventory = new Inventory();
    Map<String, Customer> customers = new HashMap<>();
    Map<String, Rental> rentals = new HashMap<>();
    Map<String, Charge> charges = new HashMap<>();

    public void addProduct(Product product) {
        inventory.addProduct(product);
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.id, customer);
    }

    public void removeProduct(String productId) {
        inventory.removeProduct(productId);
    }

    public void rentProduct(String productId, String customerId, Date rentalDate, Date dueDate) {
        Product product = inventory.products.get(productId);
        if (product != null && product.available) {
            product.rent();
            String rentalId = UUID.randomUUID().toString();
            Rental rental = new Rental(rentalId, productId, customerId, rentalDate, dueDate);
            rentals.put(rentalId, rental);
        }
    }

    public void createCharge(String customerId, double amount, Date date) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            customer.addCharge(amount);
            String chargeId = UUID.randomUUID().toString();
            Charge charge = new Charge(chargeId, customerId, amount, date);
            charges.put(chargeId, charge);
        }
    }

    public double getCustomerBalance(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            return customer.balance;
        }
        return 0.0;
    }

    public List<Rental> getOverdueRentals(Date currentDate) {
        List<Rental> overdueRentals = new ArrayList<>();
        for (Rental rental : rentals.values()) {
            if (rental.returnDate == null && rental.dueDate.before(currentDate)) {
                overdueRentals.add(rental);
            }
        }
        return overdueRentals;
    }

    public List<Rental> getRentalsByCustomer(String customerId) {
        List<Rental> customerRentals = new ArrayList<>();
        for (Rental rental : rentals.values()) {
            if (rental.customerId.equals(customerId)) {
                customerRentals.add(rental);
            }
        }
        return customerRentals;
    }

    public List<Product> getAvailableProducts() {
        return inventory.getAvailableProducts();
    }

    public List<Bike> getAvailableBikes() {
        return inventory.getAvailableBikes();
    }

    public List<Scooter> getAvailableScooters() {
        return inventory.getAvailableScooters();
    }

    public List<Bike> getBikesBySize(Size size) {
        return inventory.getBikesBySize(size);
    }
}
