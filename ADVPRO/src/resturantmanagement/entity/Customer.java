package resturantmanagement.entity;

public class Customer {
    private int customerId;
    private String name;
    private int tableId;

    public Customer(int customerId, String name, int tableId) {
        this.customerId = customerId;
        this.name = name;
        this.tableId = tableId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public int getTableId() {
        return tableId;
    }
}
