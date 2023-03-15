package set39;

public class CricketScores {
    public static void main(String[] args) {
        String scores = "1 . 2 . 4 3 6 W 1 . N . 2 1";
        String[] scoreList = scores.split(" ");

        int p1Score = 0;
        int p2Score = 0;
        int extras = 0;
        boolean isP1 = true;
        boolean isP2 = true;

        for (String score : scoreList) {
            if (score.equals(".")) {
                    // Dot ball, no score
            } else if (score.equals("W")) {
                extras += 1; // Wide ball, add 1 to Extras
            } else if (score.equals("N")) {
                extras += 1; // No ball, add 1 to Extras
                ; // Free hit, add 1 to Player 2's score
            } else {
                int runs = Integer.parseInt(score);

                if (isP1) {
                    if(runs%2!=0) {
                        isP1 = false;
                    }
                    p1Score += runs;
                } else  {
                    if(runs%2!=0) {
                        isP1 = true;
                    }
                    p2Score += runs;
                }
                // Switch to other player
            }
        }

        System.out.println("Player 1 Score: " + p1Score);
        System.out.println("Player 2 Score: " + p2Score);
        System.out.println("Extras: " + extras);
    }
}
