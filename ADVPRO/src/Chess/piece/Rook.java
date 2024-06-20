package Chess.piece;

import Chess.Board;

/**
 * Created by Vignesh.V on 19/06/24.
 */
public class Rook extends Piece {
    public Rook(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        if (isMoveOutOfBounds(newX, newY)) return false;
        if (x == newX || y == newY) {
            return isPathClear(newX, newY, board);
        }
        return false;
    }

    private boolean isPathClear(int newX, int newY, Board board) {
        int dx = Integer.compare(newX, x);
        int dy = Integer.compare(newY, y);
        int cx = x + dx;
        int cy = y + dy;
        while (cx != newX || cy != newY) {
            if (board.getPiece(cx, cy) != null) return false;
            cx += dx;
            cy += dy;
        }
        return board.getPiece(newX, newY) == null || board.getPiece(newX, newY).getColor() != color;
    }
}
