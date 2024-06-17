package BookStore;

public class UserObserver implements OrderObserver {
    private String name;

    public UserObserver(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println("Notification received by " + name);
    }
}
