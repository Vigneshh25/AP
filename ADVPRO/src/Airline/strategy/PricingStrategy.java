package Airline.strategy;

import Airline.model.SeatType;

import java.util.Date;

public interface PricingStrategy {
    double calculatePrice(SeatType seatType, Date bookingDate, int remainingSeats);
}
