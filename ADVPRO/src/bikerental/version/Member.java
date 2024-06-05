package bikerental.version;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vignesh.V on 05/06/24.
 */ // Member class
class Member {
    private String memberId;
    private String name;
    private String email;
    private List<Reservation> reservations;

    public Member(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.reservations = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    // Additional methods as needed
}
