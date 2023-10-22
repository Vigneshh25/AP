package TrainBookingWithCabin;


class Passenger {
    private  String name;
    private  Gender gender;
    int age;
    private boolean confirmed;
    private boolean RAC;
    private Seat seat;
    private SeatType preferredBerth;

    public Passenger(String name, int age, Gender gender, SeatType seatType) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.preferredBerth = seatType;
        this.confirmed = false;
        this.RAC = false;
        this.seat = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }


    public Gender getGender() {
        return gender;
    }

    public boolean isfemale() {
        return this.gender.equals(Gender.FEMALE);
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
        return "Passenger [name=" + name + ", gender=" + gender + ", age=" + age + ", " + confirmedStatus + ", " + racStatus + ", " + seat + "]";
    }


    public boolean isWaitingList() {
        return this.RAC || this.confirmed;
    }
}