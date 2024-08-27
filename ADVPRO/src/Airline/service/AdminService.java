package Airline.service;

import Airline.model.Flight;
import Airline.model.Aircraft;
import Airline.model.Schedule;
import Airline.factory.FlightFactory;
import Airline.strategy.PricingStrategy;

public class AdminService {
    private NotificationService notificationService;

    public AdminService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public Flight createFlight(String flightNumber, Aircraft aircraft, Schedule schedule, FlightFactory flightFactory, PricingStrategy pricingStrategy) {
        return flightFactory.createFlight(flightNumber, aircraft, schedule, pricingStrategy);
    }

    public void cancelFlight(Flight flight) {
        // Logic to cancel flight
        notificationService.notifyAllObservers("Flight " + flight + " has been cancelled.");
    }
}
