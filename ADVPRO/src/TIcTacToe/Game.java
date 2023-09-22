package TIcTacToe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    Player[] players;
    public Game() {
        players = new Player[2];
        Player p1 = new Player("Vicky",'X');
        players[0]=p1;
        Player p2 = new Player("Vishal",'O');
        players[1]=p2;
    }

    public void start() {
        Scanner sc =new Scanner(System.in);
        System.out.println("------WELCOME------- \n");
        Board board = new Board(9);
        board.printBoard();
        int movecount = 0;
        Player player;
        String winner = null;
        while(winner == null)
        {
            movecount++;
            if(movecount%2!=0)
                player = players[0];
            else
                player = players[1];
            System.out.print(
                    player.name + "'s turn; enter a slot number to place "
                            + player.sign + " in:");
            while (true)
            {
                int n = sc.nextInt();
                try {
                    if(!(n>0&&n<=10)) {
                        System.out.println("Enter Valid Number");
                        continue;
                    }
                }
                catch (InputMismatchException e)
                {
                    continue;
                }
                if(board.board[n-1].equals(String.valueOf(n)))
                {
                    board.board[n-1] = String.valueOf(player.sign);
                    board.printBoard();
                    break;
                }
                else
                {
                    System.out.println("slot Already Taken...!");
                    System.out.print("Enter Valid Slot :  ");
                }
            }
            winner = board.checkResult(player);
        }
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println(
                    "It's a draw! Thanks for playing.");
        }
        // For winner -to display Congratulations! message.
        else {
            System.out.println(
                    "Congratulations! " + winner
                            + "'s have won! Thanks for playing.");
        }
    }
}