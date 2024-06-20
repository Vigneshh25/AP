package Chess;

import Chess.piece.Color;
import Chess.piece.Piece;

import java.util.Scanner;

import java.util.Scanner;

public class Game {
    private Board board;
    private Color currentPlayer;
    private Scanner scanner;

    public Game() {
        board = new Board();
        currentPlayer = Color.WHITE;
        scanner = new Scanner(System.in);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            board.display();
            System.out.println(currentPlayer + "'s turn. Enter move (e.g., e2 e4):");
            String move = scanner.nextLine().trim().toLowerCase();
            if (move.equals("exit")) {
                break;
            }
            if (move.length() != 5 || move.charAt(2) != ' ') {
                System.out.println("Invalid move format. Use format 'e2 e4'.");
                continue;
            }
            int startX = move.charAt(0) - 'a';
            int startY = move.charAt(1) - '1';
            int endX = move.charAt(3) - 'a';
            int endY = move.charAt(4) - '1';

            try {
                board.movePiece(startX, startY, endX, endY);
                switchPlayer();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }


    private void switchPlayer() {
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
}

