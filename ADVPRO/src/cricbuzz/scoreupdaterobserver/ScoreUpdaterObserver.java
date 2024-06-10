package cricbuzz.scoreupdaterobserver;

import cricbuzz.innings.Ball;

public interface ScoreUpdaterObserver {
    void update(Ball ball);
}
