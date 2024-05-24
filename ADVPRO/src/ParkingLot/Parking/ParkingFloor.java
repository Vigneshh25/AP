package ParkingLot.Parking;

import java.util.Map;
import java.util.TreeMap;

public class ParkingFloor {

    public static int floorNumber;

    private int floor;
    private  Map<Integer, ParkingSlot> slots;

    public ParkingFloor() {
        floorNumber++;
        this.floor = floorNumber;
        Map<Integer, ParkingSlot> slotMap = new TreeMap<>();
        for (int i = 1; i <= 5; i++) {
            if (i <= 2) slotMap.put(i, new ParkingSlot(i, VehicleType.BIKE));
            else if (i <= 4) slotMap.put(i, new ParkingSlot(i, VehicleType.CAR));
            else slotMap.put(i, new ParkingSlot(i, VehicleType.TRUCK));
        }
        this.slots = slotMap;
    }

    public String generateLiveMap() {
        StringBuilder liveMap = new StringBuilder();
        liveMap.append("Live Parking Map - Floor ").append(floorNumber).append("\n");

        int maxSlotNumber = slots.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);

        for (int i = 1; i <= maxSlotNumber; i++) {
            ParkingSlot slot = slots.getOrDefault(i, new ParkingSlot(i, VehicleType.CAR));
            liveMap.append("|");

            if (slot.isOccupied()) {
                liveMap.append(slot.getVehicle().getRegistrationNumber());
            } else {

                liveMap.append(" ");
            }

            liveMap.append(" ");
        }

        return liveMap.toString();
    }

    public void addParkingSlot(int slotNumber, VehicleType allowedVehicleType) {
        ParkingSlot slot = new ParkingSlot(slotNumber, allowedVehicleType);
        slots.put(slotNumber, slot);
    }

    public Map<Integer, ParkingSlot> getSlots() {
        return slots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public ParkingSlot getParkingSlotByNumber(int slotNumber) {
        if (slotNumber >= 1 && slotNumber <= slots.size()) {
            return slots.get(slotNumber);
        }
        return null;
    }

    public ParkingSlot getNextAvailableSlot() {
        for (ParkingSlot slot : slots.values()) {
            if (!slot.isOccupied()) {
                return slot;
            }
        }
        return null;
    }
}
