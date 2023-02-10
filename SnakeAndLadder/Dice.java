package snake;

import java.util.Random;

public class Dice {
    int size;

    public Dice(int size) {
        this.size = size;
    }

    public int rolldice() {
        Random rand = new Random();
        return rand.nextInt(this.size)+1;
    }
}
