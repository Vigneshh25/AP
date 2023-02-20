import java.util.Scanner;

public class LiftSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[] liftPositions = new int[]{0, 0, 0, 0, 0};
        int userSourceFloor, userDestinationFloor, nearestLiftIndex, nearestLiftPosition;
        boolean isUserInsideLift = false;

        while (true) {
            System.out.print("Enter source floor (0-10): ");
            userSourceFloor = input.nextInt();
            if (userSourceFloor < 0 || userSourceFloor > 10) {
                System.out.println("Invalid floor. Try again.");
                continue;
            }

            System.out.print("Enter destination floor (0-10): ");
            userDestinationFloor = input.nextInt();
            if (userDestinationFloor < 0 || userDestinationFloor > 10) {
                System.out.println("Invalid floor. Try again.");
                continue;
            }

            // Check if the source floor is within the allowed range for each lift
            if (userSourceFloor >= 0 && userSourceFloor <= 5) {
                // Restrict L1 and L2 to floors 0-5
                liftPositions[0] = Math.max(liftPositions[0], userSourceFloor);
                liftPositions[1] = Math.max(liftPositions[1], userSourceFloor);
            } else if (userSourceFloor >= 6 && userSourceFloor <= 10) {
                // Restrict L3 and L4 to floors 6-10
                liftPositions[2] = Math.max(liftPositions[2], userSourceFloor);
                liftPositions[3] = Math.max(liftPositions[3], userSourceFloor);
            } else {
                // Allow L5 to go to any floor
                liftPositions[4] = Math.max(liftPositions[4], userSourceFloor);
            }

            // Find the nearest lifts to the user's source floor
            nearestLiftIndex = -1;
            nearestLiftPosition = Integer.MAX_VALUE;
            for (int i = 0; i < liftPositions.length; i++) {
                int liftPosition = liftPositions[i];
                int distance = Math.abs(liftPosition - userSourceFloor);
                if (distance < nearestLiftPosition) {
                    nearestLiftIndex = i;
                    nearestLiftPosition = distance;
                }
            }

            // Check if there is another lift with the same distance
            boolean isAnotherLiftNearby = false;
            for (int i = 0; i < liftPositions.length; i++) {
                if (i != nearestLiftIndex && Math.abs(liftPositions[i] - userSourceFloor) == nearestLiftPosition) {
                    isAnotherLiftNearby = true;
                    break;
                }
            }

            // If only one lift is nearest, assign that lift to the user
            if (!isAnotherLiftNearby) {
                System.out.printf("L%d is assigned.%n", nearestLiftIndex + 1);
                liftPositions[nearestLiftIndex] = userSourceFloor;
            } else {
                // If two lifts are nearest, check the direction of the user's requirement
                boolean isUserGoingUp = userDestinationFloor > userSourceFloor;
                int firstLiftPosition = liftPositions[nearestLiftIndex];
                int secondLiftIndex = -1;
                int secondLiftPosition = Integer.MAX_VALUE;
                for (int i = 0; i < liftPositions.length; i++) {
                    if (i != nearestLiftIndex && Math.abs(liftPositions[i] - userSourceFloor) == nearestLiftPosition) {
                        int currentLiftPosition = liftPositions[i];
                       //boolean isLiftGoingUp = currentLiftPosition < liftPositions[i];
                        boolean isLiftGoingUp = currentLiftPosition < userSourceFloor;
                        if (isUserGoingUp == isLiftGoingUp) {
                            secondLiftIndex = i;
                            secondLiftPosition = currentLiftPosition;
                            break;
                        } else if (Math.abs(currentLiftPosition - userSourceFloor) < Math.abs(secondLiftPosition - userSourceFloor)) {
                            secondLiftIndex = i;
                            secondLiftPosition = currentLiftPosition;
                        }
                    }
                }
                // If there is no lift going in the same direction, assign the nearest lift
                if (secondLiftIndex == -1) {
                    System.out.printf("L%d is assigned.%n", nearestLiftIndex + 1);
                    liftPositions[nearestLiftIndex] = userSourceFloor;
                } else {
                    System.out.printf("L%d is assigned.%n", secondLiftIndex + 1);
                    liftPositions[secondLiftIndex] = userSourceFloor;
                }

                // Move the assigned lift to the user's source floor and then to the destination floor
                int assignedLiftPosition = liftPositions[isAnotherLiftNearby ? secondLiftIndex : nearestLiftIndex];
                System.out.printf("Lift    : L1  L2  L3  L4  L5%n");
                for (int i = 0; i < liftPositions.length; i++) {
                    System.out.printf("Floor: %2d ", liftPositions[i]);
                }
                System.out.println();



                while (assignedLiftPosition != userSourceFloor) {
                    if (assignedLiftPosition < userSourceFloor) {
                        assignedLiftPosition++;
                    } else {
                        assignedLiftPosition--;
                    }
                    liftPositions[isAnotherLiftNearby ? secondLiftIndex : nearestLiftIndex] = assignedLiftPosition;
                    System.out.printf("Lift   : L1 L2 L3 L4 L5%n");
                    for (int i = 0; i < liftPositions.length; i++) {
                        System.out.printf("Floor: %d   ", liftPositions[i]);
                    }
                    System.out.println();
                }

                while (assignedLiftPosition != userDestinationFloor) {
                    if (assignedLiftPosition < userDestinationFloor) {
                        assignedLiftPosition++;
                    } else {
                        assignedLiftPosition--;
                    }
                    liftPositions[isAnotherLiftNearby ? secondLiftIndex : nearestLiftIndex] = assignedLiftPosition;
                    System.out.printf("Lift   : L1 L2 L3 L4 L5%n");
                    for (int i = 0; i < liftPositions.length; i++) {
                        System.out.printf("Floor: %d   ", liftPositions[i]);
                    }
                    System.out.println();
                }

                System.out.print("Do you want to exit (Y/N)? ");
                String exitChoice = input.next();
                if (exitChoice.equalsIgnoreCase("Y")) {
                    break;
                }
                isUserInsideLift = false;
            }
        }
    }
}


