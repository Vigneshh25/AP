package set39;

public class Cricket {

    public static void cricketScores(String scoreTimeline) {
        int p1Score = 0;
        int p2Score = 0;
        int extras = 0;
        boolean isP1Batting = true;

        for (int i = 0; i < scoreTimeline.length(); i++) {
            char c = scoreTimeline.charAt(i);
            if (c == 'W') {
                extras++;
            } else if (c == 'N') {
                extras++;
            } else if (c == '.') {
                // do nothing
            } else {
                int score = Character.getNumericValue(c);
                if (isP1Batting) {
                    p1Score += score;
                    isP1Batting = false;
                } else {
                    p2Score += score;
                    isP1Batting = true;
                }
            }
        }

        System.out.println("P1: " + p1Score);
        System.out.println("P2: " + p2Score);
        System.out.println("Extras: " + extras);
    }

    public static void main(String[] args) {
        String scoreTimeline = "1 . 2 . 4 3 6 W 1 . N . 2 1";
        cricketScores(scoreTimeline);
// prints:
// P1: 8
// P2: 12
// Extras: 2

    }

}
