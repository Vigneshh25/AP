package snake;

public class Board {
    int boardSize;
    Cells[] cells;
    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.cells = new Cells[boardSize+1];
        initializeBoard();
    }
    private void initializeBoard()
    {
        for(int i=0;i<boardSize;i++)
            cells[i] = new Cells(i,i,CellType.NORMAL);
        cells[boardSize] = new Cells(boardSize,boardSize,CellType.TARGET);
        addsnake(new Snake(13,2));
        addsnake(new Snake(11,3));
        addsnake(new Snake(12,1));
        addLadder(new Ladder(5,13));
        addLadder(new Ladder(2,11));
        addLadder(new Ladder(4,12));
    }
    private void addLadder(Ladder ladder) {
        cells[ladder.start] = ladder;
    }
    private void addsnake(Snake snake) {
        cells[snake.start] = snake;
    }
    public Cells getPoisition(int newPosition) {
        return cells[newPosition];
    }
}
