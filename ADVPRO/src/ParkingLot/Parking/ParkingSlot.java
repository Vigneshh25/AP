package ParkingLot.Parking;

/**
 * Created by Vignesh.V on 24/05/24.
 */
class ParkingSlot {
    private final int slotNumber;
    private final VehicleType allowedVehicleType;
    private boolean occupied;

    private Vehicle vehicle;

    public ParkingSlot(int slotNumber, VehicleType allowedVehicleType) {
        this.slotNumber = slotNumber;
        this.allowedVehicleType = allowedVehicleType;
        this.occupied = false;
        this.vehicle = null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public VehicleType getAllowedVehicleType() {
        return allowedVehicleType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy(Vehicle vehicle) {
        occupied = true;
        this.vehicle = vehicle;
    }

    public void vacate() {
        occupied = false;
        this.vehicle = null;
    }

}
