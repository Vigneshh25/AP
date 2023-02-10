package snake;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.addPlayers(new Player("Vicky"));
        game.addPlayers(new Player("Vishal"));

        game.start();

    }
}
