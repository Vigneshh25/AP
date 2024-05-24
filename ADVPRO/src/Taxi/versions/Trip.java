package Taxi.versions;

/**
 * Created by Vignesh.V on 24/05/24.
 */
class Trip {
    private int bookingID;
    private int customerID;
    private char from;
    private char to;
    private int pickupTime;
    private int dropTime;
    private int amount;

    public Trip(int customerID, char from, char to, int pickupTime, int dropTime, int amount) {
        this.bookingID = customerID;
        this.customerID = customerID;
        this.from = from;
        this.to = to;
        this.pickupTime = pickupTime;
        this.dropTime = dropTime;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return bookingID + "\t" + customerID + "\t" + from + "\t" + to + "\t" + pickupTime + "\t" + dropTime + "\t" + amount;
    }
}
