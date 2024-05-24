package Train.versions;

/**
 * Created by Vignesh.V on 24/05/24.
 */
class Passenger {
    static int totalLowerBerths = 21;
    static int totalSLBerths = 21;
    static int totalOtherBerths = 21;
    static int totalRACTickets = 18;
    static int totalWaitingListTickets = 10;
    static int seatNumberCounter = 1;

    String name;
    int age;
    String gender;
    String berthPreference;
    int seatNumber;

    public Passenger(String name, int age, String gender, String berthPreference) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
        this.seatNumber = seatNumberCounter++;
    }
}
