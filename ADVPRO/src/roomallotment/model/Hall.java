package roomallotment.model;

import java.util.HashMap;
import java.util.Map;

public class Hall {
    private int hallId;
    private String hallName;
    private int totalSlots;
    private int rows;
    private int benchesPerRow;
    private Map<String, boolean[]> slotAvailability; // Key: "date-am/pm", Value: Array of slots

    public Hall(int hallId, String hallName, int totalSlots, int rows, int benchesPerRow) {
        this.hallId = hallId;
        this.hallName = hallName;
        this.totalSlots = totalSlots;
        this.rows = rows;
        this.benchesPerRow = benchesPerRow;
        this.slotAvailability = new HashMap<>();
    }

    public boolean isSlotAvailable(String timeSlot, int slotNumber) {
        return !slotAvailability.containsKey(timeSlot) || !slotAvailability.get(timeSlot)[slotNumber - 1];
    }

    public void reserveSlot(String timeSlot, int slotNumber) {
        slotAvailability.putIfAbsent(timeSlot, new boolean[2*totalSlots]);
        slotAvailability.get(timeSlot)[slotNumber - 1] = true;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getBenchesPerRow() {
        return benchesPerRow;
    }

    public void setBenchesPerRow(int benchesPerRow) {
        this.benchesPerRow = benchesPerRow;
    }

    public Map<String, boolean[]> getSlotAvailability() {
        return slotAvailability;
    }

    public void setSlotAvailability(Map<String, boolean[]> slotAvailability) {
        this.slotAvailability = slotAvailability;
    }
// Getters and setters
}
