package Lift;

import java.util.Scanner;

public class LiftSystem {
    private static final int NUM_LIFTS = 5;
    private static final int NUM_FLOORS = 10;
    private static final String[] LIFT_NAMES = {"L1", "L2", "L3", "L4", "L5"};

    private static int[] liftCapacity;
    private static Lift[] lifts;
    private final FloorStrategy floorStrategy;
    private final LiftMovementStrategy movementStrategy;

    public LiftSystem(int[] liftCapacity, FloorStrategy floorStrategy, LiftMovementStrategy movementStrategy) {
        LiftSystem.liftCapacity = liftCapacity;
        this.floorStrategy = floorStrategy;
        this.movementStrategy = movementStrategy;
        initializeLifts();
    }

    public static void displayLiftPositions() {
        System.out.print("Lifts  : ");
        for (String name : LIFT_NAMES) {
            System.out.print(name + " ");
        }
        System.out.println();
        System.out.print("Floors : ");
        for (int i = 0; i < NUM_LIFTS; i++) {
            System.out.print(lifts[i].getPosition() + "   ");
        }
        System.out.println();
    }

    private void initializeLifts() {
        lifts = new Lift[NUM_LIFTS];
        for (int i = 0; i < NUM_LIFTS; i++) {
            lifts[i] = new Lift(LIFT_NAMES[i]);
        }
    }

    public void assignLift(int startFloor, int destFloor, int numPeople) {
        int minDist = Integer.MAX_VALUE;
        int closestLift = -1;
        boolean sameDirection = false;
        int minStops = Integer.MAX_VALUE;

        for (int i = 0; i < NUM_LIFTS; i++) {
            if (!lifts[i].isBusy() && numPeople <= liftCapacity[i] && floorStrategy.isLiftAllowedOnFloor(i, startFloor)) {
                int dist = Math.abs(lifts[i].getPosition() - startFloor);
                if (dist < minDist) {
                    minDist = dist;
                    closestLift = i;
                    sameDirection = (startFloor > lifts[i].getPosition() && destFloor < startFloor) || (startFloor < lifts[i].getPosition() && destFloor > startFloor);
                }

                if (closestLift != -1) {
                    int numStops = (sameDirection) ? Math.abs(destFloor - lifts[closestLift].getPosition()) : Math.abs(startFloor - lifts[closestLift].getPosition()) + Math.abs(destFloor - startFloor);

                    if (numStops < minStops || (numStops == minStops && dist < minDist)) {
                        minStops = numStops;
                    }
                }
            }
        }

        if (closestLift == -1) {
            System.out.println("All lifts are busy, please wait");
            return;
        }

        lifts[closestLift].setBusy(true);
        System.out.println(LIFT_NAMES[closestLift] + " is assigned");
        movementStrategy.moveLift(lifts[closestLift], destFloor);
    }
    public static void main(String[] args) {
        LiftSystem system = new LiftSystem(new int[]{10, 10, 10, 10, 10}, new DefaultFloorStrategy(), new DefaultLiftMovementStrategy());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            LiftSystem.displayLiftPositions();
            System.out.print("Enter start floor and destination floor: ");
            int startFloor = scanner.nextInt();
            int destFloor = scanner.nextInt();
            System.out.print("Enter number of people traveling: ");
            int numPeople = scanner.nextInt();
            system.assignLift(startFloor, destFloor, numPeople);
        }
    }
}
