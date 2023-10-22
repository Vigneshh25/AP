package TrainBookingWithCabin;

import java.util.List;
import java.util.Random;

class Ticket {
    private int PNR;
    private List<Passenger> passengers;
    private List<Seat> seats;

    public Ticket(int PNR, List<Passenger> passengers, List<Seat> seats) {
        this.PNR = PNR;
        this.passengers = passengers;
        this.seats = seats;
    }

    public static int generatePNR() {
        Random random = new Random();
        return 10000 + random.nextInt(90000);
    }

    public int getPNR() {
        return PNR;
    }

    public void setPNR(int PNR) {
        this.PNR = PNR;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public static void printTicket(Ticket ticket) {
        System.out.println("============================================");
        System.out.println("             Railway Ticket                 ");
        System.out.println("============================================");
        System.out.println("PNR: " + ticket.PNR);
        System.out.println("Passengers:");
        for (Passenger passenger : ticket.passengers) {
            System.out.println("Name: " + passenger.getName());
            System.out.println("Age: " + passenger.getAge());
            System.out.println("Gender: " + passenger.getGender());
            System.out.println("Berth Preference: " + passenger.getPreferredBerth());
            System.out.println("CabinNumber: " + passenger.getSeat().cabinNumber);
            System.out.println("Seat Number: "+passenger.getSeat().getSeatNumber());
            System.out.println("Allotted Berth: "+passenger.getSeat().getSeatType());
            System.out.println("------------------------------------------------");
        }
    }
}