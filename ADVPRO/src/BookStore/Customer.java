package BookStore;

public class Customer  extends User{
    private String name;
    private String email;

    public Customer(String name, String email) {
        super(name,email);
        this.name = name;
        this.email = email;
    }

    public void displayInfo() {
        System.out.println("Customer Name: " + name);
        System.out.println("Customer Email: " + email);
    }
}
