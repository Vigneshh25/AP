package bikerental.version;

/**
 * Created by Vignesh.V on 05/06/24.
 */ // Decorator pattern for additional options
abstract class ReservationOption {
    protected Reservation reservation;

    public ReservationOption(Reservation reservation) {
        this.reservation = reservation;
    }

    public abstract double getCost();
}
