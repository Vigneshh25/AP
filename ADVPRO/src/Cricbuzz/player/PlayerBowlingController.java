package Cricbuzz.player;

import java.util.*;

public class PlayerBowlingController {
    Deque<Player> bowlersList;
    Map<Player, Integer> bowlerVsOverCount;
    Player currentBowler;

    public PlayerBowlingController(List<Player> bowlers) {
        setBowlersList(bowlers);
    }

    private void setBowlersList(List<Player> bowlersList) {
        this.bowlersList = new LinkedList<>();
        bowlerVsOverCount = new HashMap<>();
        for (Player bowler : bowlersList) {
            this.bowlersList.addLast(bowler);
            bowlerVsOverCount.put(bowler, 0);
        }
    }

    public void getNextBowler(int maxOverCountPerBowler) {

        Player Player = bowlersList.poll();
        if(bowlerVsOverCount.get(Player)+1 == maxOverCountPerBowler) {
            currentBowler = Player;
        }
        else {
            currentBowler = Player;
            bowlersList.addLast(Player);
            bowlerVsOverCount.put(Player, bowlerVsOverCount.get(Player)+1);
        }
    }

    public Player getCurrentBowler(){
        return currentBowler;
    }

}
