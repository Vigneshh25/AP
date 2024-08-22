package vendingmachine.state;

import vendingmachine.VendingMachine;
import vendingmachine.model.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductSelectedState implements VendingMachineState {
    private final VendingMachine vendingMachine;
    private Map<Product, Integer> selectedProducts;
    private final String stateName;

    public ProductSelectedState(VendingMachine vendingMachine, String stateName) {
        this.vendingMachine = vendingMachine;
        this.selectedProducts = new HashMap<>();
        this.stateName = stateName;
    }

    @Override
    public void insertCoin(int value) {
        System.out.println("Product already selected. Please select a product first.");
    }

    @Override
    public void selectProduct(String productCode, int quantity) {
        Product product = vendingMachine.getProduct(productCode);
        if (selectedProducts.containsKey(product)) {
            // If product already selected, add to existing quantity
            selectedProducts.compute(product, (k, currentQuantity) -> currentQuantity + quantity);
        } else {
            // Otherwise, add new entry for the product with quantity
            selectedProducts.put(product, quantity);
        }
        System.out.println("Product selected: " + product.getName() + " - Quantity: " + quantity);
    }

    @Override
    public void dispenseProduct() {
        for (Map.Entry<Product, Integer> entry : selectedProducts.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            // Check if product is available in inventory (although it should be checked before selecting)
            if (product.getQuantity() >= quantity) {
                vendingMachine.dispenseProduct(product, quantity); // Dispense the product
            } else {
                System.out.println("Error: Product " + product.getProductCode() + " is out of stock.");
            }
        }
        selectedProducts.clear(); // Clear selected products after dispensing
    }

    @Override
    public void cancelTransactions() {
        int refundedAmount = vendingMachine.cancelTransactions();
        System.out.println("Transaction cancelled. Refunded amount: " + refundedAmount);
        selectedProducts.clear(); // Clear selected products on cancellation
        vendingMachine.setState(vendingMachine.getNoCoinInsertedState()); // Return to no coin inserted state
    }

    @Override
    public String getState() {
        return stateName;
    }
}
