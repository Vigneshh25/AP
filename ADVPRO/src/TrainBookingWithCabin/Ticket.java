package TrainBookingWithCabin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class Ticket {
    private int PNR;

    private List<Passenger> passengerList;
    public Ticket(int pnr,List<Passenger> passengers) {
        this.PNR = pnr;
        this.passengerList = passengers;
    }

    public int getPNR() {
        return PNR;
    }

    public static int generatePNR() {
        Random random = new Random();
        return 10000 + random.nextInt(10000);
    }

    public static void printTicket(Ticket ticket) {
        List<Passenger> passengers = ticket.getPassengers();
        int sNo = 1;
        System.out.println("s.NO  Name   Age   Cabin    Seat   Berth");
        for (Passenger passenger : passengers)
        {
            Seat seat = passenger.getSeat();
            System.out.println(sNo++ +"     "+passenger.getName()+"   "+passenger.getAge()+"    "+seat.cabinNumber+"      "+seat.getSeatNumber()+"      "+seat.getSeatType());
        }
        System.out.println();
    }

    public List<Passenger> getPassengers() {
        return passengerList;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "PNR=" + PNR +
                ", passengerList=" + passengerList +
                '}';
    }
}