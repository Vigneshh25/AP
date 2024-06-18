package Airline;

class Baggage {
    private Passenger passenger;
    private double weight; // in kilograms

    public Baggage(Passenger passenger, double weight) {
        this.passenger = passenger;
        this.weight = weight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Baggage{" +
                "passenger=" + passenger +
                ", weight=" + weight +
                '}';
    }
}
