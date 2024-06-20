package Chess.piece;

import Chess.Board;

/**
 * Created by Vignesh.V on 19/06/24.
 */

public class Knight extends Piece {
    public Knight(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        if (isMoveOutOfBounds(newX, newY)) return false;
        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);
        if (dx * dy == 2) {
            Piece target = board.getPiece(newX, newY);
            return target == null || target.getColor() != color;
        }
        return false;
    }
}

