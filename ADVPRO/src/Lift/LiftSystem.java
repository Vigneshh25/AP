package Lift;

import java.util.*;

class LiftSystem {
    private static final int NUM_LIFTS = 5;
    private static final int NUM_FLOORS = 10;
    private static final String[] LIFT_NAMES = {"L1", "L2", "L3", "L4", "L5"};

    private int[] liftPositions;
    private boolean[] liftBusy;
    private int[] liftCapacity;

    public LiftSystem(int[] liftCapacity) {
        this.liftCapacity = liftCapacity;
        liftPositions = new int[NUM_LIFTS];
        liftBusy = new boolean[NUM_LIFTS];
    }

    public void displayLiftPositions() {
        System.out.print("Lifts  : ");
        for (String name : LIFT_NAMES) {
            System.out.print(name + " ");
        }
        System.out.println();
        System.out.print("Floors : ");
        for (int i = 0; i < NUM_LIFTS; i++) {
            System.out.print(liftPositions[i] + "   ");
        }
        System.out.println();
    }

    public void assignLift(int startFloor, int destFloor, int numPeople) {
        int minDist = Integer.MAX_VALUE;
        int closestLift = -1;
        boolean sameDirection = false;
        int minStops = Integer.MAX_VALUE;
        for (int i = 0; i < NUM_LIFTS; i++) {
            if (!liftBusy[i] && numPeople <= liftCapacity[i]) { // check if lift has enough capacity
                int dist = Math.abs(liftPositions[i] - startFloor);
                if (dist < minDist && isLiftAllowedOnFloor(i, startFloor)) {
                    minDist = dist;
                    closestLift = i;
                    if (startFloor > liftPositions[i] && destFloor < startFloor) {
                        // User wants to go down and the lift is moving down
                        sameDirection = true;
                    } else if (startFloor < liftPositions[i] && destFloor > startFloor) {
                        // User wants to go up and the lift is moving up
                        sameDirection = true;
                    } else {
                        sameDirection = false;
                    }
                }
                if (closestLift != -1) {
                    int numStops = 0;
                    if (sameDirection) {
                        numStops = Math.abs(destFloor - liftPositions[closestLift]);
                    } else {
                        numStops = Math.abs(startFloor - liftPositions[closestLift]) + Math.abs(destFloor - startFloor);
                    }
                    if (numStops < minStops) {
                        minStops = numStops;
                    } else if (numStops == minStops && dist < minDist) {
                        closestLift = i;
                    }
                }
            }
        }
        if (closestLift == -1) {
            System.out.println("All lifts are busy, please wait");
            return;
        }
        liftBusy[closestLift] = true;
        System.out.println(LIFT_NAMES[closestLift] + " is assigned");
        moveLift(closestLift, destFloor);
    }
    private boolean isLiftAllowedOnFloor(int liftIndex, int floor) {
        if (liftIndex == 0 || liftIndex == 1) {
            return (floor >= 0 && floor <= 5);
        } else if (liftIndex == 2 || liftIndex == 3) {
            return (floor >= 6 && floor <= 10);
        } else if (liftIndex == 4) {
            return (floor >= 0 && floor <= 10);
        } else {
            return false;
        }
    }


    private void moveLift(int liftIndex, int destFloor) {
        int currentFloor = liftPositions[liftIndex];
        int direction = Integer.compare(destFloor, currentFloor);
        while (liftPositions[liftIndex] != destFloor) {
            liftPositions[liftIndex] += direction;
            displayLiftPositions();
            try {
                Thread.sleep(1000); // simulate lift movement
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        liftBusy[liftIndex] = false;
        
    }

    public static void main(String[] args) {
        LiftSystem system = new LiftSystem(new int[]{10, 10, 10, 10, 10});
        Scanner scanner = new Scanner(System.in);
        while (true) {
            system.displayLiftPositions();
            System.out.print("Enter start floor and destination floor: ");
            int startFloor = scanner.nextInt();
            int destFloor = scanner.nextInt();
            System.out.print("Enter number of people traveling: ");
            int numPeople = scanner.nextInt();
            system.assignLift(startFloor, destFloor, numPeople);
        }
    }
}
