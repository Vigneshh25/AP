package snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Board board;
    List<Player> players;
    public Game() {
        this.board = new Board(100);
        this.players = new ArrayList<>();
    }
    void addPlayers(Player name)
    {
        this.players.add(name);
    }
    public void start() {
        boolean isGameOver = false;
        Dice dice = new Dice(6);
        while (!isGameOver)
        {
            Scanner sc = new Scanner(System.in);
            for (Player player:players)
            {

                System.out.println(player.name+" Turns His current position - "+player.position);
                System.out.print ("Press any key to roll Dice : ");
                String  n = sc.next();
                int newPosition = dice.rolldice();
                System.out.println("Dice : "+newPosition);
                isGameOver = player.move(board,newPosition);
                System.out.println("---------------------------------");
                if(isGameOver)
                {
                    System.out.println(player.name+" WIns !");
                    break;
                }
            }
        }
    }
}
