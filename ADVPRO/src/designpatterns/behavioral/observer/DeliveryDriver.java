package designpatterns.behavioral.observer;

class DeliveryDriver implements Observer {
    private final String driverName;

    public DeliveryDriver(String name) {
        this.driverName = name;
    }

    @Override
    public void update(Order order) {
        System.out.println("Driver " + driverName + ": Order #" + order.getId() + " is now " + order.getStatus() + ".");
    }
}
