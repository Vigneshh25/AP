package Chess.piece;

import Chess.Board;

/**
 * Created by Vignesh.V on 19/06/24.
 */
public class Queen extends Piece {
    public Queen(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        if (isMoveOutOfBounds(newX, newY)) return false;
        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);
        if (dx == dy || dx == 0 || dy == 0) {
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
