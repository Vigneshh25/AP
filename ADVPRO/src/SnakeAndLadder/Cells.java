package SnakeAndLadder;



enum CellType
{
    NORMAL,SNAKE,LADDER,TARGET;
}
public class Cells {
    int start;
    int end;
    CellType type;

    public Cells(int start, int end, CellType type) {
        this.start = start;
        this.end = end;
        this.type = type;
    }
}
