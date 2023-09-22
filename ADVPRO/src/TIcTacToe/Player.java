package TIcTacToe;

public class Player {
    String name;
    char sign;

    public Player(String name, char sign) {
        this.name = name;
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", sign=" + sign +
                '}';
    }
}
