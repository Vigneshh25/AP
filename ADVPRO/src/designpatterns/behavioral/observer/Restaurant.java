package designpatterns.behavioral.observer;

class Restaurant implements Observer {
    private final String restaurantName;

    public Restaurant(String name) {
        this.restaurantName = name;
    }

    @Override
    public void update(Order order) {
        System.out.println("Restaurant " + restaurantName + ": Order #" + order.getId() + " is now " + order.getStatus() + ".");
    }
}
