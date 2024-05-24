package ParkingLot.Parking;

/**
 * Created by Vignesh.V on 24/05/24.
 */
public class ParkingTicket {
    private final int floorNumber;
    private final int slotNumber;
    private final VehicleType vehicleType;

    public ParkingTicket(int floorNumber, int slotNumber, VehicleType vehicleType) {
        this.floorNumber = floorNumber;
        this.slotNumber = slotNumber;
        this.vehicleType = vehicleType;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
