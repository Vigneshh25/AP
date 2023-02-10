package ParkingLot;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter width and length of area");

        int width = scanner.nextInt();
        int length = scanner.nextInt();

        if (width <= 1 || length <= 1){
            System.out.println("Width or length is too smaller");
            System.exit(0);
        }

        length += 2;
        width += 3;

        Car car = new Car(length,width);
        car.addParkingPlaces();

        do {
            System.out.println("1.Entry");
            System.out.println("2.Exit");
            System.out.println("3.Print Floor Map");
            System.out.println("4.Break Progress");

            int choice = scanner.nextInt();
            int carNumber = -1;

            if (choice == 1 || choice == 2){
                System.out.println("Enter the car number");
                carNumber = scanner.nextInt();

                int digits = (int) Math.log10(carNumber) + 1;

                if (digits != 4){
                    System.out.println("Car number should be 4 digits");
                    continue;
                }
            }

            switch (choice){
                case 1:
                    car.entry(carNumber);
                    car.printFloorMap();
                    break;
                case 2:
                    car.exit(carNumber);
                    car.printFloorMap();
                    break;
                case 3:
                    car.printFloorMap();
                    break;
                case 4:
                    System.out.println("Exited");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice...");
            }
            System.out.println();
            System.out.println();
        }while (true);
    }
}