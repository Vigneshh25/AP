package Airline.model;

public class Pilot {
    private String name;

    public Pilot(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
