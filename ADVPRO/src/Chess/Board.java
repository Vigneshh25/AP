package Chess;

import Chess.piece.*;

public class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        setupPieces();
    }

    public Piece[][] getBoard() {
        return board;
    }

    private void setupPieces() {
        for (int i = 0; i < 8; i++) {
            board[i][1] = new Pawn(i, 1, Color.WHITE);
            board[i][6] = new Pawn(i, 6, Color.BLACK);
        }

        board[0][0] = new Rook(0, 0, Color.WHITE);
        board[7][0] = new Rook(7, 0, Color.WHITE);
        board[0][7] = new Rook(0, 7, Color.BLACK);
        board[7][7] = new Rook(7, 7, Color.BLACK);

        board[1][0] = new Knight(1, 0, Color.WHITE);
        board[6][0] = new Knight(6, 0, Color.WHITE);
        board[1][7] = new Knight(1, 7, Color.BLACK);
        board[6][7] = new Knight(6, 7, Color.BLACK);

        board[2][0] = new Bishop(2, 0, Color.WHITE);
        board[5][0] = new Bishop(5, 0, Color.WHITE);
        board[2][7] = new Bishop(2, 7, Color.BLACK);
        board[5][7] = new Bishop(5, 7, Color.BLACK);

        board[3][0] = new Queen(3, 0, Color.WHITE);
        board[3][7] = new Queen(3, 7, Color.BLACK);

        board[4][0] = new King(4, 0, Color.WHITE);
        board[4][7] = new King(4, 7, Color.BLACK);
    }

    public Piece getPiece(int x, int y) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return null;
        }
        return board[x][y];
    }

    public void movePiece(int startX, int startY, int endX, int endY) {
        Piece piece = board[startX][startY];
        if (piece == null) {
            throw new IllegalArgumentException("No piece at position (" + startX + ", " + startY + ")");
        }
        if (!piece.isValidMove(endX, endY, this)) {
            throw new IllegalArgumentException("Invalid move for piece at position (" + startX + ", " + startY + ")");
        }
        board[endX][endY] = piece;
        piece.move(endX, endY);
        board[startX][startY] = null;
    }

    public void display() {
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                Piece piece = board[x][y];
                if (piece == null) {
                    System.out.print(". ");
                } else {
                    String symbol = piece.getClass().getSimpleName().substring(0, 1);
                    symbol = piece.getColor() == Color.WHITE ? symbol.toUpperCase() : symbol.toLowerCase();
                    System.out.print(symbol + " ");
                }
            }
            System.out.println(y + 1);
        }
        System.out.println(" a b c d e f g h");
    }
}
