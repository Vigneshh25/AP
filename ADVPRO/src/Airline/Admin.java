package Airline;

class Admin {
    private NotificationService notificationService;

    public Admin(NotificationService notificationService) {
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

