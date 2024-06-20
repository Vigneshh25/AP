package Chess.piece;

import Chess.Board;

/**
 * Created by Vignesh.V on 19/06/24.
 */
public abstract class Piece {
    protected int x, y;
    protected Color color;

    public Piece(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract boolean isValidMove(int newX, int newY, Board board);

    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public boolean isMoveOutOfBounds(int newX, int newY) {
        return newX < 0 || newX >= 8 || newY < 0 || newY >= 8;
    }
}

