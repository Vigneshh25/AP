package vendingmachine;

import vendingmachine.model.Product;
import vendingmachine.model.VendingMachineObserver;
import vendingmachine.repository.ProductInventory;
import vendingmachine.service.PaymentHandler;
import vendingmachine.state.CoinInsertedState;
import vendingmachine.state.NoCoinInsertedState;
import vendingmachine.state.ProductSelectedState;
import vendingmachine.state.VendingMachineState;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private final VendingMachineState noCoinInsertedState;
    private final VendingMachineState coinInsertedState;
    private final VendingMachineState productSelectedState;
    private VendingMachineState currentState;
    private int balance;
    private Product selectedProduct;
    private static volatile VendingMachine instance;
    private ProductInventory productInventory;
    private List<VendingMachineObserver> observers;
    private PaymentHandler paymentHandler;
    private int totalSales;



    private VendingMachine() {
        this.noCoinInsertedState = new NoCoinInsertedState(this,"NoCoinInserted");
        this.coinInsertedState = new CoinInsertedState(this,"CoinInserted");
        this.productSelectedState = new ProductSelectedState(this,"ProductSelected");
        this.currentState = noCoinInsertedState;
        this.balance = 0;
        this.selectedProduct = null;
        this.observers = new ArrayList<>();
    }

    public static synchronized VendingMachine getInstance() {
        if (instance == null)
            instance = new VendingMachine();
        return instance;
    }

    public void setPaymentHandler(PaymentHandler paymentHandler) {
        this.paymentHandler = paymentHandler;
    }

    public PaymentHandler getPaymentHandler() {
        return paymentHandler;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void addSale(int amount) {
        totalSales += amount;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void setProductInventory(ProductInventory inventory) {
        this.productInventory = inventory;
    }

    public void insertCoin(int value) {
        currentState.insertCoin(value);
    }

    public void selectProduct(String productCode, int quantity) {
        currentState.selectProduct(productCode, quantity);
    }
    public void dispenseProduct() {
        currentState.dispenseProduct();
    }

    public void cancelTransaction() {
        currentState.cancelTransactions();
    }

    public int getBalance() {
        return balance;
    }

    public void registerObserver(VendingMachineObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(VendingMachineObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        System.out.println(observers);
        for (VendingMachineObserver observer : observers) {
            observer.update(selectedProduct.getProductCode(), selectedProduct.getQuantity());
        }
    }

    public void addBalance(int value) {
        balance += value;
    }

    public VendingMachineState getCurrentState() {
        return currentState;
    }

    public Product getProduct(String productCode) {
        return productInventory.getProducts(productCode);
    }

    public void dispenseProduct(Product product, int quantity) {
        if (product.getQuantity() >= quantity && balance >= product.getPrice() * quantity) {
            if (paymentHandler != null && paymentHandler.processPayment(product.getPrice() * quantity)) {
                product.decrementQuantity(quantity);
                balance -= product.getPrice() * quantity; // Deduct balance
                addSale(product.getPrice() * quantity);
                System.out.println("Dispensing product: " + product.getName() + " - Quantity: " + quantity);

                // Notify observers (Admin) if product quantity is zero after dispensing
                if (product.getQuantity() == 0) {
                    notifyObservers();
                }

                // Check if there's remaining balance
                if (balance > 0) {
                    setState(coinInsertedState); // Stay in coin inserted state
                } else {
                    setState(noCoinInsertedState); // No more balance, return to no coin inserted state
                }
            } else {
                System.out.println("Not enough quantity available for " + product.getName());
            }
        } else {
            if (product.getQuantity() == 0) {
                System.out.println("Product out of stock.");
                // Notify observers (Admin) if product is out of stock
                notifyObservers();
            } else {
                System.out.println("Insufficient balance.");
            }
        }
    }

    public int cancelTransactions() {
        int refundedAmount = balance;
        balance = 0;
        selectedProduct = null;
        currentState = noCoinInsertedState; // Reset state to no coin inserted
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
