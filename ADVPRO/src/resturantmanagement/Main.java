package resturantmanagement;

import resturantmanagement.entity.*;
import resturantmanagement.service.*;
import resturantmanagement.strategy.*;

import java.util.Map;


public class Main {
    public static void main(String[] args) {
        // Initialize services
        TableService tableService = new TableService();
        MenuService menuService = new MenuService();
        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService();
        KitchenService kitchenService = new KitchenService();

        // Add tables
        tableService.addTable(new Table(1, 4));
        tableService.addTable(new Table(2, 6));
        tableService.addTable(new Table(3, 2));

        System.out.println("Tables in the restaurant:");
        for (Table table : tableService.getTables()) {
            System.out.println("Table ID: " + table.getTableId() + ", Capacity: " + table.getCapacity());
        }

        // Add menu items
        MenuItem item1 = new MenuItem("Paneer Tikka", 200.0, MenuItem.Type.VEG, MenuItem.Category.STARTER);
        MenuItem item2 = new MenuItem("Chicken Biryani", 350.0, MenuItem.Type.NON_VEG, MenuItem.Category.MAIN_COURSE);
        MenuItem item3 = new MenuItem("Gulab Jamun", 100.0, MenuItem.Type.VEG, MenuItem.Category.DESSERT);
        MenuItem item4 = new MenuItem("Fish Fry", 250.0, MenuItem.Type.NON_VEG, MenuItem.Category.STARTER);
        menuService.addMenuItem(item1);
        menuService.addMenuItem(item2);
        menuService.addMenuItem(item3);
        menuService.addMenuItem(item4);

        System.out.println("\nMenu items in the restaurant:");
        for (MenuItem item : menuService.getMenuItems()) {
            System.out.println("Name: " + item.getName() + ", Price: " + item.getPrice() + ", Type: " + item.getType() + ", Category: " + item.getCategory());
        }

        // Filter menu items
        System.out.println("\nFiltered menu items (VEG, STARTER):");
        for (MenuItem item : menuService.filterMenuItems(MenuItem.Type.VEG, MenuItem.Category.STARTER)) {
            System.out.println("Name: " + item.getName() + ", Price: " + item.getPrice());
        }

        // Create orders
        Order order1 = orderService.createOrder(1);
        order1.addItem(item1, 2);
        order1.addItem(item2, 1);

        Order order2 = orderService.createOrder(2);
        order2.addItem(item3, 3);
        order2.addItem(item4, 2);

        Order order3 = orderService.createOrder(3);
        order3.addItem(item1, 1);
        order3.addItem(item4, 1);

        // Add orders to tables
        tableService.getTable(1).addOrder(order1);
        tableService.getTable(2).addOrder(order2);
        tableService.getTable(3).addOrder(order3);

        // Display orders for each table
        System.out.println("\nOrders for each table:");
        for (Table table : tableService.getTables()) {
            System.out.println("Table ID: " + table.getTableId());
            for (Order order : table.getOrders()) {
                System.out.println("  Order ID: " + order.getOrderId());
                for (Map.Entry<MenuItem, Integer> entry : order.getItems().entrySet()) {
                    System.out.println("    Item: " + entry.getKey().getName() + ", Quantity: " + entry.getValue());
                }
            }
        }

        // Calculate and process payments
        double bill1 = orderService.calculateBill(order1);
        System.out.println("\nBill for Order ID " + order1.getOrderId() + ": " + bill1);
        paymentService.setPaymentStrategy(new CashPayment());
        paymentService.processPayment(bill1);

        double bill2 = orderService.calculateBill(order2);
        System.out.println("Bill for Order ID " + order2.getOrderId() + ": " + bill2);
        paymentService.setPaymentStrategy(new CardPayment());
        paymentService.processPayment(bill2);

        double bill3 = orderService.calculateBill(order3);
        System.out.println("Bill for Order ID " + order3.getOrderId() + ": " + bill3);
        paymentService.setPaymentStrategy(new CashPayment());
        paymentService.processPayment(bill3);

        // Kitchen operations
        kitchenService.addOrder(order1);
        kitchenService.addOrder(order2);
        kitchenService.addOrder(order3);

        System.out.println("\nPending orders in the kitchen:");
        for (Order order : kitchenService.getPendingOrders()) {
            System.out.println("Order ID: " + order.getOrderId());
        }

        kitchenService.markOrderPrepared(order1.getOrderId());

        System.out.println("\nPending orders after marking Order ID " + order1.getOrderId() + " as prepared:");
        for (Order order : kitchenService.getPendingOrders()) {
            System.out.println("Order ID: " + order.getOrderId());
        }
    }
}
