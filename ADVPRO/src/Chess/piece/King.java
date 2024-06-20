package Chess.piece;

import Chess.Board;

/**
 * Created by Vignesh.V on 19/06/24.
 */
public class King extends Piece {
    public King(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        if (isMoveOutOfBounds(newX, newY)) return false;
        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);
        Piece target = board.getPiece(newX, newY);
        return (dx <= 1 && dy <= 1) && (target == null || target.getColor() != color);
    }
}

