package Airline.model;

public class Aircraft {
    private String model;
    private int capacity;

    public Aircraft(String model, int capacity) {
        this.model = model;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return model + " (Capacity: " + capacity + ")";
    }
}
