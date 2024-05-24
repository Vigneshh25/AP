package Lift;

public class DefaultLiftMovementStrategy implements LiftMovementStrategy {
    @Override
    public void moveLift(Lift lift, int destFloor) {
        int currentFloor = lift.getPosition();
        int direction = Integer.compare(destFloor, currentFloor);
        new Thread(() -> {
            while (lift.getPosition() != destFloor) {
                lift.setPosition(lift.getPosition() + direction);
//            LiftSystem.displayLiftPositions();
                try {
                    Thread.sleep(1000); // simulate lift movement
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lift.setBusy(false);}).start();
    }
}
