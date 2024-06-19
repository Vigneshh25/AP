package Lift;

import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

interface FloorStrategy {
    boolean isLiftAllowedOnFloor(int liftIndex, int floor);
}

interface LiftMovementStrategy {
    void moveLift(Lift lift, int destFloor);
}

class Lift {
    private final String name;
    private int position;
    private boolean busy;
    private final Lock lock;

    public Lift(String name) {
        this.name = name;
        this.position = 0;
        this.busy = false;
        this.lock = new ReentrantLock();
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

    public Lock getLock() {
        return lock;
    }
}


