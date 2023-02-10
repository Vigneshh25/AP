package set4;

public class PetrolBunk {
    // fields for the name of the petrol bunk, distance from home, and capacity
    private String name;
    private int distance;
    private int capacity;

    // constructor for the PetrolBunk class
    public PetrolBunk(String name, int distance, int capacity) {
        this.name = name;
        this.distance = distance;
        this.capacity = capacity;
    }

    // getters for the fields
    public String getName() { return name; }
    public int getDistance() { return distance; }
    public int getCapacity() { return capacity; }
}

