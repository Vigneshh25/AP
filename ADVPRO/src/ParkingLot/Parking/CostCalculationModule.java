package ParkingLot.Parking;

public class CostCalculationModule {
    public double calculateParkingFee(int duration, VehicleType vehicleType) {
        // Simplified fee calculation based on duration and vehicle type
        double baseFee = 5.0;
        double rateMultiplier = (vehicleType == VehicleType.TRUCK) ? 1.5 : 1.0;
        return baseFee * duration * rateMultiplier;
    }
}
