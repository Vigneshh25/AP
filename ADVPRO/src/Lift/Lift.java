package Lift;

import java.util.Scanner;

interface FloorStrategy {
    boolean isLiftAllowedOnFloor(int liftIndex, int floor);
}

interface LiftMovementStrategy {
    void moveLift(Lift lift, int destFloor);
}

public class Lift {
    private final String name;
    private int position;
    private boolean busy;

    public Lift(String name) {
        this.name = name;
        this.position = 0;
        this.busy = false;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public void displayLiftPositions() {
        System.out.println(name + ": " + position + "   ");
    }
}


