package Lift;

import java.util.Scanner;

public class LiftSystem {
    private static final int NUM_LIFTS = 5;
    private static final String[] LIFT_NAMES = {"L1", "L2", "L3", "L4", "L5"};

    private int[] liftCapacity;
    private static Lift[] lifts;
    private final FloorStrategy floorStrategy;
    private final LiftMovementStrategy movementStrategy;

    public LiftSystem(int[] liftCapacity, FloorStrategy floorStrategy, LiftMovementStrategy movementStrategy) {
        this.liftCapacity = liftCapacity;
        this.floorStrategy = floorStrategy;
        this.movementStrategy = movementStrategy;
        this.lifts = new Lift[NUM_LIFTS];
        initializeLifts();
    }

    private void initializeLifts() {
        for (int i = 0; i < NUM_LIFTS; i++) {
            lifts[i] = new Lift(LIFT_NAMES[i]);
        }
    }

    public static void displayLiftPositions() {
        System.out.print("Lifts  : ");
        for (Lift lift : lifts) {
            System.out.print(lift.getName() + " ");
        }
        System.out.println();
        System.out.print("Floors : ");
        for (Lift lift : lifts) {
            System.out.print(lift.getPosition() + "   ");
        }
        System.out.println();
    }

    public void assignLift(int startFloor, int destFloor, int numPeople) {
        int closestLiftIndex = -1;
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < NUM_LIFTS; i++) {
            Lift lift = lifts[i];
            lift.getLock().lock();
            try {
                if (!lift.isBusy() && numPeople <= liftCapacity[i] && floorStrategy.isLiftAllowedOnFloor(i, startFloor)) {
                    int dist = Math.abs(lift.getPosition() - startFloor);
                    if (dist < minDist) {
                        minDist = dist;
                        closestLiftIndex = i;
                    }
                }
            } finally {
                lift.getLock().unlock();
            }
        }

        if (closestLiftIndex == -1) {
            System.out.println("All lifts are busy, please wait");
            return;
        }

        Lift assignedLift = lifts[closestLiftIndex];
        assignedLift.setBusy(true);
        System.out.println(assignedLift.getName() + " is assigned");
        movementStrategy.moveLift(assignedLift, destFloor);
    }

    public static void main(String[] args) {
        LiftSystem system = new LiftSystem(
                new int[]{10, 10, 10, 10, 10},
                new DefaultFloorStrategy(),
                new DefaultLiftMovementStrategy()
        );

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
