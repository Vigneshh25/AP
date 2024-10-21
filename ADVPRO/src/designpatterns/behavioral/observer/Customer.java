package designpatterns.behavioral.observer;

class Customer implements Observer {
    private final String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void update(Order order) {
        System.out.println("Hello, " + name + "! Order #" + order.getId() + " is now " + order.getStatus() + ".");
    }
}
