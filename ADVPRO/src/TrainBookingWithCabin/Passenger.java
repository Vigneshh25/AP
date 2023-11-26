package TrainBookingWithCabin;


class Passenger {
    static int id = 0;
    private int passengerId;
    private int age;
    private String name;
    private boolean confirmed;
    private boolean RAC;
    private Seat seat;
    private SeatType preferredBerth;

    public Passenger(String name, int age, SeatType seatType) {
        this.passengerId = ++id;
        this.name = name;
        this.age = age;
        this.preferredBerth = seatType;
        this.confirmed = false;
        this.RAC = false;
        this.seat = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isRAC() {
        return RAC;
    }

    public void setRAC(boolean RAC) {
        this.RAC = RAC;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public SeatType getPreferredBerth() {
        return preferredBerth;
    }

    public void setPreferredBerth(SeatType preferredBerth) {
        this.preferredBerth = preferredBerth;
    }

    @Override
    public String toString() {
        String confirmedStatus = confirmed ? "Confirmed" : "Not Confirmed";
        String racStatus = RAC ? "RAC" : "Not RAC";
        return "Passenger [name=" + name + ", age=" + age + ", " + confirmedStatus + ", " + racStatus + ", " + seat + "]";
    }


    public boolean isWaitingList() {
        return this.RAC || this.confirmed;
    }
}