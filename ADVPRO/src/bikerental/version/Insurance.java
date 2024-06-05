package bikerental.version;

/**
 * Created by Vignesh.V on 05/06/24.
 */
class Insurance extends ReservationOption {
    private double cost;

    public Insurance(Reservation reservation, double cost) {
        super(reservation);
        this.cost = cost;
    }

    @Override
    public double getCost() {
        return cost;
    }
}
