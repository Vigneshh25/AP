package TrainBookingWithCabin;

import java.util.List;

public class Coach {
    int coachNumber;
    List<Cabin> cabinList;

    public Coach(int coachNumber, List<Cabin> cabinList) {
        this.coachNumber = coachNumber;
        this.cabinList = cabinList;
    }
}
