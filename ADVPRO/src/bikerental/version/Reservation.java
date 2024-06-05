package bikerental.version;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vignesh.V on 05/06/24.
 */ // Reservation class
class Reservation {
    private String reservationId;
    private Member member;
    private Vehicle vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Insurance> insurances;
    private List<Equipment> equipments;
    private List<Service> services;

    public Reservation(String reservationId, Member member, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        this.reservationId = reservationId;
        this.member = member;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.insurances = new ArrayList<>();
        this.equipments = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    public String getReservationId() {
        return reservationId;
    }

    public Member getMember() {
        return member;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void addInsurance(Insurance insurance) {
        insurances.add(insurance);
    }

    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
    }

    public void addService(Service service) {
        services.add(service);
    }

    // Additional methods as needed
}
