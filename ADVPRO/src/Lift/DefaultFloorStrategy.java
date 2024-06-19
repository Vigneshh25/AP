package Lift;

class DefaultFloorStrategy implements FloorStrategy {
    @Override
    public boolean isLiftAllowedOnFloor(int liftIndex, int floor) {
        if (liftIndex == 0 || liftIndex == 1) {
            return floor >= 0 && floor <= 5;
        } else if (liftIndex == 2 || liftIndex == 3) {
            return floor >= 6 && floor <= 10;
        } else if (liftIndex == 4) {
            return floor >= 0 && floor <= 10;
        }
        return false;
    }
}
