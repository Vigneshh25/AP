package Invoice.versions;

import java.util.*;


import java.util.List;
import java.util.Scanner;

public class InvoiceSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Customer> customers = new ArrayList<>(); // Add your list of customers here
    private static final List<Item> items = Arrays.asList( // Add your list of items here
            new Item(400, "HeadPhone"),
            new Item(1000, "Speaker"),
            new Item(500, "SD card"),
            new Item(600, "Battery")
    );

    private static int customerId = 0;
    private static int invoiceId = 0;

    public static void main(String[] args) {
        boolean loop = true;

        while (loop) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    addInvoice();
                    break;
                case 3:
                    addItemToInvoice(new Invoice());
                    break;
                case 4:
                    listAllCustomers();
                    break;
                case 5:
                    listAllInvoices();
                    break;
                case 6:
                    listInvoicesForCustomer();
                    break;
                case 7:
                    displayInvoice();
                    break;
                case 8:
                    loop = false;
                    System.out.println("Exiting the Invoice System. Goodbye!");
                    break;
                default:
                    System.out.println("Enter Correct Option");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("_____________INVOICE SYSTEM______________");
        System.out.println("1.Add Customer");
        System.out.println("2.Add Invoice");
        System.out.println("3.Add Item to Invoice");
        System.out.println("4.List all Customers");
        System.out.println("5.List all Invoices");
        System.out.println("6.List all invoices of a Customer");
        System.out.println("7.Display an invoice");
        System.out.println("8.Exit");
        System.out.print("Enter the Option: ");
    }

    private static void addCustomer() {
        System.out.println("Enter customer name:");
        String name = scanner.next();
        System.out.print("Enter address:");
        String address = scanner.next();
        System.out.print("Enter Mobile no:");
        String mobileNo = scanner.next();

        Customer newCustomer = new Customer(name, address, mobileNo);
        customers.add(newCustomer);

        System.out.println("Your id is " + customerId);
        customerId++;
    }

    private static void addInvoice() {
        System.out.println("1.Existing Customer");
        System.out.println("2.New Customer");
        int choose = scanner.nextInt();

        Customer selectedCustomer;
        if (choose == 1) {
            selectedCustomer = selectExistingCustomer();
        } else if (choose == 2) {
            selectedCustomer = addNewCustomer();
        } else {
            System.out.println("Enter Valid options");
            return;
        }

        Invoice newInvoice = createInvoice(selectedCustomer);
        addItemToInvoice(newInvoice);

        System.out.println("Your invoice no: " + invoiceId);
        System.out.println("Total Price: Rs." + newInvoice.getTotalPrice());
        System.out.println("Number of Items: " + newInvoice.getNoOfItems());
        invoiceId++;
    }

    private static Customer selectExistingCustomer() {
        System.out.print("Enter Customer ID:");
        int customerIdInput = scanner.nextInt();
        if (customerIdInput >= customers.size()) {
            System.out.println("Enter Valid id");
            return null;
        }
        return customers.get(customerIdInput);
    }

    private static Customer addNewCustomer() {
        System.out.println("Enter customer name:");
        String customerName = scanner.next();
        System.out.println("Enter address:");
        String customerAddress = scanner.next();
        System.out.println("Enter Mobile no:");
        String customerMobileNo = scanner.next();

        Customer newCustomer = new Customer(customerName, customerAddress, customerMobileNo);
        customers.add(newCustomer);

        return newCustomer;
    }

    private static Invoice createInvoice(Customer customer) {
        Invoice newInvoice = new Invoice(invoiceId);
        customer.addInvoice(newInvoice);
        return newInvoice;
    }

    private static void addItemToInvoice(Invoice invoice) {
        int select = 1;
        while (select == 1) {
            System.out.println("Select the Item");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + "." + items.get(i).getItemName() + " Rs." + items.get(i).getPrice());
            }

            int n = scanner.nextInt();
            if (n > items.size()) {
                System.out.println("Invalid Option");
                break;
            }

            Item selectedItem = items.get(n - 1);
            invoice.addItem(selectedItem);

            System.out.println("Are you want to add one more item? Enter 1 for yes, 0 for no");
            select = scanner.nextInt();
        }
    }

    private static void listAllCustomers() {
        System.out.println("List of all Customers:");
        customers.forEach(System.out::println);
    }

    private static void listAllInvoices() {
        System.out.println("List of all Invoices:");
        customers.forEach(customer -> {
            for (Invoice invoice : customer.getInvoices()) {
                System.out.println("Customer: " + customer.getName() + ", Invoice: " + invoice);
            }
        });
    }

    private static void listInvoicesForCustomer() {
        System.out.println("Enter Customer id:");
        int customer_Id = scanner.nextInt();

        if (customer_Id < customers.size()) {
            Customer selectedCustomerForList = customers.get(customer_Id);
            System.out.println("Invoices for Customer " + selectedCustomerForList.getName() + ":");
            selectedCustomerForList.getInvoices().forEach(System.out::println);
        } else {
            System.out.println("Invalid Customer Id");
        }
    }

    private static void displayInvoice() {
        System.out.println("Enter the invoice No:");
        int invoiceNo = scanner.nextInt();

        if (invoiceNo < invoiceId) {
            Customer customerForDisplay = findCustomerForDisplay(invoiceNo);

            if (customerForDisplay != null) {
                System.out.println("Customer: " + customerForDisplay.getName());
                System.out.println("Invoice: " + invoiceNo);

                Invoice selectedInvoice = findInvoiceForDisplay(customerForDisplay, invoiceNo);

                if (selectedInvoice != null) {
                    System.out.println("Items in Invoice:");
                    selectedInvoice.getItems().forEach(System.out::println);
                    System.out.println("Total Price: Rs." + selectedInvoice.getTotalPrice());
                    System.out.println("Number of Items: " + selectedInvoice.getNoOfItems());
                }
            } else {
                System.out.println("Invoice not found for any customer");
            }
        } else {
            System.out.println("Invalid Invoice Id");
        }
    }

    private static Customer findCustomerForDisplay(int invoiceNo) {
        for (Customer customer : customers) {
            for (Invoice invoice : customer.getInvoices()) {
                if (invoice.getInvoiceId() == invoiceNo) {
                    return customer;
                }
            }
        }
        return null;
    }

    private static Invoice findInvoiceForDisplay(Customer customer, int invoiceNo) {
        for (Invoice invoice : customer.getInvoices()) {
            if (invoice.getInvoiceId() == invoiceNo) {
                return invoice;
            }
        }
        return null;
    }
}

class Customer {
    private String name;
    private String address;
    private String mobileNo;
    private List<Invoice> invoices;

    public Customer(String name, String address, String mobileNo) {
        this.name = name;
        this.address = address;
        this.mobileNo = mobileNo;
        this.invoices = new ArrayList<>();
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", invoices=" + invoices +
                '}';
    }
}

class Invoice {
    private int invoiceId;
    private List<Item> items;

    public Invoice() {
    }

    public Invoice(int invoiceId) {
        this.invoiceId = invoiceId;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getTotalPrice() {
        return items.stream().mapToInt(Item::getPrice).sum();
    }

    public int getNoOfItems() {
        return items.size();
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", items=" + items +
                '}';
    }
}

class Item {
    private int price;
    private String itemName;

    public Item(int price, String itemName) {
        this.price = price;
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public String toString() {
        return "Item{" +
                "price=" + price +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}

