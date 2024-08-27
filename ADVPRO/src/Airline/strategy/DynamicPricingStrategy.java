package Airline.strategy;

import Airline.model.SeatType;

import java.util.Date;

public class DynamicPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(SeatType seatType, Date bookingDate, int remainingSeats) {
        double basePrice;
        switch (seatType) {
            case FIRST: basePrice = 1000.0; break;
            case BUSINESS: basePrice = 500.0; break;
            case ECONOMY: default: basePrice = 200.0; break;
        }
        double demandFactor = (100.0 - remainingSeats) / 100.0;
        double timeFactor = 1.0 - ((new Date().getTime() - bookingDate.getTime()) / (1000 * 60 * 60 * 24 * 30.0));
        return basePrice * (1.0 + demandFactor) * timeFactor;
    }
}
