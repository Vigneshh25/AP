package cricbuzz.innings;

import cricbuzz.entity.Team;
import cricbuzz.entity.Wicket;
import cricbuzz.enums.BallType;
import cricbuzz.enums.RunType;
import cricbuzz.enums.WicketType;
import cricbuzz.scoreupdaterobserver.BattingScoreUpdater;
import cricbuzz.scoreupdaterobserver.BowlingScoreUpdater;
import cricbuzz.scoreupdaterobserver.ScoreUpdaterObserver;
import cricbuzz.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Ball {
    public int ballNumber;
    public BallType ballType;
    public RunType runType;
    public Player playedBy;
    public Player bowledBy;
    public Wicket wicket;
    List<ScoreUpdaterObserver> scoreUpdaterObserverList = new ArrayList<>();

    public Ball(int ballNumber) {
        this.ballNumber = ballNumber;
        scoreUpdaterObserverList.add(new BowlingScoreUpdater());
        scoreUpdaterObserverList.add(new BattingScoreUpdater());
    }

    public void startBallDelivery(Team battingTeam, Team bowlingTeam, OverDetails over) {

        playedBy = battingTeam.getStriker();
        this.bowledBy = over.bowledBy;
        //THROW BALL AND GET THE BALL TYPE, assuming here that ball type is always NORMAL
        ballType = BallType.NORMAL;

        //wicket or no wicket
        if (isWicketTaken()) {
            runType = RunType.ZERO;
            //considering only BOLD
            wicket = new Wicket(WicketType.BOWLED, bowlingTeam.getCurrentBowler(), over,this);
            //making only striker out for now
            battingTeam.setStriker(null);
        } else {
            runType = getRunType();

            if(runType == RunType.ONE || runType == RunType.THREE) {
                //swap striket and non striker
                Player temp = battingTeam.getStriker();
                battingTeam.setStriker(battingTeam.getNonStriker());
                battingTeam.setNonStriker(temp);
            }
        }

        //update player scoreboard
        notifyUpdaters(this);
    }

    private void notifyUpdaters(Ball ballDetails){

        for(ScoreUpdaterObserver observer : scoreUpdaterObserverList) {
            observer.update(ballDetails);
        }
    }

    private RunType getRunType() {

        double val = Math.random();
        if (val <= 0.2) {
            return RunType.ONE;
        } else if (val >= 0.3 && val <= 0.5) {
            return RunType.TWO;
        } else if (val >= 0.6 && val <= 0.8) {
            return RunType.FOUR;
        } else {
            return RunType.SIX;
        }
    }

    private boolean isWicketTaken() {
        //random function return value between 0 and 1
        if (Math.random() < 0.2) {
            return true;
        } else {
            return false;
        }
    }

}
