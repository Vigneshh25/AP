package L;

import java.util.Scanner;

 class LiftSystemmm {
    public static void main(String[] args) {
        //initialize the lift positions as 0 for all lifts
        int[] liftPositions = new int[]{0, 0, 0, 0, 0};

        //initialize the lift names
        String[] liftNames = new String[]{"L1", "L2", "L3", "L4", "L5"};

        //display the initial lift positions
        System.out.println("Lift   : " + String.join(" ", liftNames));
        System.out.println("Floor: " + String.join("   ", Integer.toString(0).repeat(5).split("")));

        //create a scanner object to take user input
        Scanner scanner = new Scanner(System.in);

        //loop to take user input continuously
        while (true) {
            //ask for input from user
            System.out.println("\nPlease enter the source and destination floors (separated by a space): ");
            int sourceFloor = scanner.nextInt();
            int destinationFloor = scanner.nextInt();

            //check for invalid input
            if (sourceFloor < 0 || sourceFloor > 10 || destinationFloor < 0 || destinationFloor > 10) {
                System.out.println("Invalid input! Please enter floors between 0 and 5.");
                continue;
            }

            //assign the lift to the user
            String assignedLift = assignLift(liftPositions, liftNames, sourceFloor, destinationFloor);

            //display the assigned lift and the updated lift positions
            System.out.println(assignedLift + " is assigned");
            System.out.println("Lift   : " + String.join(" ", liftNames));
            System.out.println("Floor: " + String.join("   ", Integer.toString(liftPositions[0]),
                    Integer.toString(liftPositions[1]), Integer.toString(liftPositions[2]),
                    Integer.toString(liftPositions[3]), Integer.toString(liftPositions[4])));
        }
    }

    //method to assign the lift to the user
    public static String assignLift(int[] liftPositions, String[] liftNames, int sourceFloor, int destinationFloor) {
        //initialize the minimum distance and the assigned lift name
        int minDistance = Integer.MAX_VALUE;
        String assignedLift = "";

        // loop through each lift to find the closest one
        for (int i = 0; i < liftPositions.length; i++) {
            // check if the lift is allowed for the source and destination floors
            if ((liftNames[i].equals("L1") || liftNames[i].equals("L2")) && (sourceFloor < 0 || sourceFloor > 5 || destinationFloor < 0 || destinationFloor > 5)) {
                continue;
            }
            if ((liftNames[i].equals("L3") || liftNames[i].equals("L4")) &&(destinationFloor==0) && (sourceFloor < 6 || sourceFloor > 10 || destinationFloor < 6 || destinationFloor > 10)) {
                continue;
            }
            if (liftNames[i].equals("L5") && (sourceFloor < 0 || sourceFloor > 10 || destinationFloor < 0 || destinationFloor > 10)) {
                continue;
            }

            // calculate the distance between the lift and the source floor
            int distance = Math.abs(liftPositions[i] - sourceFloor);

            //if the distance is less than the minimum distance and the lift is going in the right direction,
            //assign the lift to the user
            if (distance < minDistance && (destinationFloor > sourceFloor && liftPositions[i] < destinationFloor
                    || destinationFloor < sourceFloor && liftPositions[i] > destinationFloor)) {
                minDistance = distance;
                assignedLift = liftNames[i];
            }
        }

        //update the lift position to the destination floor
        liftPositions[getIndex(assignedLift, liftNames)] = destinationFloor;

        return assignedLift;
    }


     //method to get the index of a lift in the lift names array
    public static int getIndex(String liftName, String[] liftNames) {
        for (int i = 0; i < liftNames.length; i++) {
            if (liftName.equals(liftNames[i])) {
                return i;
            }
        }
        return -1;
    }
}
