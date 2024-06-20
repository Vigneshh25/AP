package Chess.piece;

import Chess.Board;

/**
 * Created by Vignesh.V on 19/06/24.
 */
public class Pawn extends Piece {
    public Pawn(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        if (isMoveOutOfBounds(newX, newY)) return false;
        int direction = color == Color.WHITE ? 1 : -1;
        if (newX == x && board.getPiece(newX, newY) == null) {
            return (newY - y == direction) || (newY - y == 2 * direction && y == (color == Color.WHITE ? 1 : 6) && board.getPiece(newX, newY - direction) == null);
        } else if (Math.abs(newX - x) == 1 && newY - y == direction) {
            Piece target = board.getPiece(newX, newY);
            return target != null && target.getColor() != color;
        }
        return false;
    }
}
