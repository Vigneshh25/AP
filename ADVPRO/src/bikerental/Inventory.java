package bikerental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Inventory {
    Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.id, product);
    }

    public void removeProduct(String productId) {
        products.remove(productId);
    }

    public List<Product> getAvailableProducts() {
        List<Product> availableProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.available) {
                availableProducts.add(product);
            }
        }
        return availableProducts;
    }

    public List<Bike> getAvailableBikes() {
        List<Bike> availableBikes = new ArrayList<>();
        for (Product product : products.values()) {
            if (product instanceof Bike && product.available) {
                availableBikes.add((Bike) product);
            }
        }
        return availableBikes;
    }

    public List<Scooter> getAvailableScooters() {
        List<Scooter> availableScooters = new ArrayList<>();
        for (Product product : products.values()) {
            if (product instanceof Scooter && product.available) {
                availableScooters.add((Scooter) product);
            }
        }
        return availableScooters;
    }

    public List<Bike> getBikesBySize(Size size) {
        List<Bike> bikesBySize = new ArrayList<>();
        for (Product product : products.values()) {
            if (product instanceof Bike && product.available && ((Bike) product).size == size) {
                bikesBySize.add((Bike) product);
            }
        }
        return bikesBySize;
    }
}
