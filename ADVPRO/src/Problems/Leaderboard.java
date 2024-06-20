package Problems;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Leaderboard {
    private Map<Integer, Integer> scores;

    public Leaderboard() {
        scores = new HashMap<>();
    }

    public void addScore(int playerId, int score) {
        scores.put(playerId, scores.getOrDefault(playerId, 0) + score);
    }

    public int top(int K) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int score : scores.values()) {
            minHeap.offer(score);
            if (minHeap.size() > K) {
                minHeap.poll();
            }
        }

        int total = 0;
        while (!minHeap.isEmpty()) {
            total += minHeap.poll();
        }

        return total;
    }

    public void reset(int playerId) {
        scores.put(playerId, 0);
    }

    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.addScore(1, 73); // leaderboard = {1: 73}
        leaderboard.addScore(2, 56); // leaderboard = {1: 73, 2: 56}
        leaderboard.addScore(3, 39); // leaderboard = {1: 73, 2: 56, 3: 39}
        leaderboard.addScore(4, 51); // leaderboard = {1: 73, 2: 56, 3: 39, 4: 51}
        leaderboard.addScore(5, 4);  // leaderboard = {1: 73, 2: 56, 3: 39, 4: 51, 5: 4}

        System.out.println(leaderboard.top(1)); // returns 73
        leaderboard.reset(1); // leaderboard = {1: 0, 2: 56, 3: 39, 4: 51, 5: 4}
        leaderboard.reset(2); // leaderboard = {1: 0, 2: 0, 3: 39, 4: 51, 5: 4}
        leaderboard.addScore(2, 51); // leaderboard = {1: 0, 2: 51, 3: 39, 4: 51, 5: 4}

        System.out.println(leaderboard.top(3)); // returns 141 = 51 + 51 + 39
    }
}
