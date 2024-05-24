package Taxi.versions;

import java.util.Scanner;

public class TaxiBookingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaxiService taxiService = new TaxiService(4);
        int customerID = 1;

        while (true) {
            System.out.println("1. Book Taxi");
            System.out.println("2. Display Taxi Details");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Pickup point: ");
                    char pickupPoint = scanner.next().charAt(0);
                    System.out.print("Enter Drop point: ");
                    char dropPoint = scanner.next().charAt(0);
                    System.out.print("Enter Pickup time: ");
                    int pickupTime = scanner.nextInt();

                    Booking.bookTaxi(customerID, pickupPoint, dropPoint, pickupTime, taxiService.getFreeTaxis(pickupTime, pickupPoint));
                    customerID++;
                    break;

                case 2:
                    taxiService.displayTaxiDetails();
                    break;

                case 0:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

