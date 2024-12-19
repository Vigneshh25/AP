package Cricbuzz.entity;


import Cricbuzz.player.Player;
import Cricbuzz.player.PlayerBattingController;
import Cricbuzz.player.PlayerBowlingController;

import java.util.List;
import java.util.Queue;

public class Team {
    public String teamName;
    public Queue<Player> playing11;
    public List<Player> bench;
    public PlayerBattingController battingController;
    public PlayerBowlingController bowlingController;
    public boolean isWinner;

    public Team(String teamName, Queue<Player> playing11, List<Player> bench, List<Player> bowlers) {
        this.teamName = teamName;
        this.playing11 = playing11;
        this.bench = bench;
        battingController = new PlayerBattingController(playing11);
        bowlingController = new PlayerBowlingController(bowlers);
    }

    public String getTeamName() {
        return teamName;
    }

    public void chooseNextBatsMan() throws Exception {
        battingController.getNextPlayer();
    }

    public void chooseNextBowler(int maxOverCountPerBowler) {
        bowlingController.getNextBowler(maxOverCountPerBowler);
    }

    public Player getStriker() {
        return battingController.getStriker();
    }

    public void setStriker(Player player) {
        battingController.setStriker(player);
    }

    public Player getNonStriker() {
        return battingController.getNonStriker();
    }

    public void setNonStriker(Player player) {
        battingController.setNonStriker(player);
    }

    public Player getCurrentBowler() {
        return bowlingController.getCurrentBowler();
    }

    public void printBattingScoreCard() {

        for (Player Player : playing11) {
            Player.printBattingScoreCard();
        }
    }

    public void printBowlingScoreCard() {

        for (Player Player : playing11) {
            if (Player.bowlingScoreCard.totalOversCount > 0) {
                Player.printBowlingScoreCard();
            }
        }
    }

    public int getTotalRuns() {
        int totalRun = 0;
        for (Player player : playing11) {

            totalRun += player.battingScoreCard.totalRuns;
        }
        return totalRun;
    }

}
