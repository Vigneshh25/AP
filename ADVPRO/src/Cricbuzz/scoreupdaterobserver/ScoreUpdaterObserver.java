package Cricbuzz.scoreupdaterobserver;

import Cricbuzz.innings.Ball;

public interface ScoreUpdaterObserver {
    void update(Ball ball);
}
