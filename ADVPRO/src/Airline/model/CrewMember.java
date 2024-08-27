package Airline.model;

public class CrewMember {
    private String name;

    public CrewMember(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
