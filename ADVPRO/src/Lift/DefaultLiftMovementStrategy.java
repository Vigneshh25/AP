package Lift;

class DefaultLiftMovementStrategy implements LiftMovementStrategy {
    @Override
    public void moveLift(Lift lift, int destFloor) {
        new Thread(() -> {
            int direction = Integer.compare(destFloor, lift.getPosition());
            while (lift.getPosition() != destFloor) {
                lift.setPosition(lift.getPosition() + direction);
                LiftSystem.displayLiftPositions();
                try {
                    Thread.sleep(1000); // Simulate lift movement
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            lift.setBusy(false);
        }).start();
    }
}
