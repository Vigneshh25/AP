package vendingmachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    private final Map<String, Product> products;
    private final VendingMachineState noCoinInsertedState;
    private final VendingMachineState coinInsertedState;
    private final VendingMachineState productSelectedState;
    private VendingMachineState currentState;
    private int balance;
    private Product selectedProduct;

    public VendingMachine() {
        this.products = new HashMap<>();
        this.noCoinInsertedState = new NoCoinInsertedState(this);
        this.coinInsertedState = new CoinInsertedState(this);
        this.productSelectedState = new ProductSelectedState(this);
        this.currentState = noCoinInsertedState;
        this.balance = 0;
        this.selectedProduct = null;
    }

    public void insertCoin(int value) {
        currentState.insertCoin(value);
    }

    public void selectProduct(String productCode) {
        currentState.selectProduct(productCode);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }

    public void cancelTransaction() {
        currentState.cancelTransactions();
    }

    public void addProduct(Product product) {
        products.put(product.getProductCode(), product);
    }

    public List<Product> getAvailableProducts() {
        return new ArrayList<>(products.values());
    }

    public int getBalance() {
        return balance;
    }

    public void addBalance(int value) {
        balance += value;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public Product getProduct(String productCode) {
        return products.get(productCode);
    }

    public void dispenseProduct(Product product) {
        product.decrementQuantity();
        balance -= product.getPrice();
        System.out.println("Dispensing product: " + product.getName());
    }

    public int cancelTransactions() {
        int refundedAmount = balance;
        balance = 0;
        selectedProduct = null;
        return refundedAmount;
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    public VendingMachineState getNoCoinInsertedState() {
        return noCoinInsertedState;
    }

    public VendingMachineState getCoinInsertedState() {
        return coinInsertedState;
    }

    public VendingMachineState getProductSelectedState() {
        return productSelectedState;
    }
}
