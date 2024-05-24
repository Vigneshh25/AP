package Ecommerce;

import java.util.*;

// Item class to represent items in the e-commerce store
class Item {
    private String name;
    private double price;
    private boolean isDiscounted;
    private double discountPercentage;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
        this.isDiscounted = false;
        this.discountPercentage = 0;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isDiscounted() {
        return isDiscounted;
    }

    public void setDiscounted(boolean isDiscounted) {
        this.isDiscounted = isDiscounted;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}

// Customer class to represent customers in the e-commerce store
class Customer {
    private String name;
    private List<Item> cart;
    private List<Item> orderHistory;

    public Customer(String name) {
        this.name = name;
        this.cart = new ArrayList<Item>();
        this.orderHistory = new ArrayList<Item>();
    }

    public String getName() {
        return name;
    }

    public void addToCart(Item item) {
        cart.add(item);
    }

    public List<Item> getCart() {
        return cart;
    }

    public void redeemDiscount(Item item) {
        if (item.isDiscounted()) {
            double discountPercentage = item.getDiscountPercentage();
            double discountAmount = (discountPercentage / 100) * item.getPrice();
            double discountedPrice = item.getPrice() - discountAmount;
            item.setPrice(discountedPrice);
        }
    }

    public void placeOrder() {
        for (Item item : cart) {
            orderHistory.add(item);
        }
        cart.clear();
    }

    public List<Item> getOrderHistory() {
        return orderHistory;
    }
}

// Admin class to represent administrators in the e-commerce store
class Admin {
    private List<Item> inventory;

    public Admin() {
        this.inventory = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public List<Item> getInventory() {
        return inventory;
    }
}

// BusRoute.shortestpath.Design_Datastructure.filesystem.Main class to run the e-commerce application
public class EcommerceApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        Customer customer = new Customer("John");

        // Add some items to the inventory
        Item item1 = new Item("Item 1", 100);
        Item item2 = new Item("Item 2", 200);
        Item item3 = new Item("Item 3", 300);
        item3.setDiscounted(true);
        item3.setDiscountPercentage(10);
        admin.addItem(item1);
        admin.addItem(item2);
        admin.addItem(item3);

        // BusRoute.shortestpath.Design_Datastructure.filesystem.Main menu
        while (true) {
            System.out.println("Welcome to the e-commerce store!");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Admin menu
                    System.out.println("Admin menu");
                    System.out.println("1. Add item to inventory");
                    System.out.println("2. View inventory");
                    System.out.println("3. Back");
                    int adminChoice = scanner.nextInt();
                    switch (adminChoice) {
                        case 1:
                            // Add item to inventory
                            System.out.print("Enter item name: ");
                            String itemName = scanner.next();
                            System.out.print("Enter item price: ");
                            double itemPrice = scanner.nextDouble();
                            Item item = new Item(itemName, itemPrice);
                            admin.addItem(item);
                            System.out.println("Item added to inventory.");
                            break;
                        case 2:
                            // View inventory
                            List<Item> inventory = admin.getInventory();
                            System.out.println("Inventory:");
                            for (Item i : inventory) {
                                System.out.println(i.getName() + " - " + i.getPrice());
                            }
                            break;
                        case 3:
                            // Back
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;
                case 2:
                    // Customer menu
                    System.out.println("Customer menu");
                    System.out.println("1. View items");
                    System.out.println("2. Add item to cart");
                    System.out.println("3. View cart");
                    System.out.println("4. Redeem discount");
                    System.out.println("5. Place order");
                    System.out.println("6. View order history");
                    System.out.println("7. Back");
                    System.out.print("Enter your choice: ");
                    int customerChoice = scanner.nextInt();
                    switch (customerChoice) {
                        case 1:
                            // View items
                            List<Item> items = admin.getInventory();
                            System.out.println("Items:");
                            for (Item i : items) {
                                System.out.println(i.getName() + " - " + i.getPrice());
                            }
                            break;
                        case 2:
                            // Add item to cart
                            List<Item> cart = customer.getCart();
                            List<Item> inventory = admin.getInventory();
                            System.out.print("Enter item name: ");
                            String itemName = scanner.next();
                            boolean found = false;
                            for (Item i : inventory) {
                                if (i.getName().equals(itemName)) {
                                    cart.add(i);
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("Item not found.");
                            } else {
                                System.out.println("Item added to cart.");
                            }
                            break;
                        case 3:
                            // View cart
                            cart = customer.getCart();
                            System.out.println("Cart:");
                            for (Item i : cart) {
                                System.out.println(i.getName() + " - " + i.getPrice());
                            }
                            break;
                        case 4:
                            // Redeem discount
                            cart = customer.getCart();
                            System.out.print("Enter item name: ");
                            itemName = scanner.next();
                            found = false;
                            for (Item i : cart) {
                                if (i.getName().equals(itemName) && i.isDiscounted()) {
                                    customer.redeemDiscount(i);
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("Item not found or not eligible for discount.");
                            } else {
                                System.out.println("Discount redeemed.");
                            }
                            break;
                        case 5:
                            // Place order
                            customer.placeOrder();
                            System.out.println("Order placed.");
                            break;
                        case 6:
                            // View order history
                            List<Item> orderHistory = customer.getOrderHistory();
                            System.out.println("Order history:");
                            for (Item i : orderHistory) {
                                System.out.println(i.getName() + " - " + i.getPrice());
                            }
                            break;
                        case 7:
                            // Back
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
            }
        }
    }
}


