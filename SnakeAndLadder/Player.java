package snake;

public class Player {
    String name;
    int position;

    public Player(String name) {
        this.name = name;
        this.position =0;
    }

    public boolean move(Board board, int number)
    {
        int newPosition = position+number;
        if(newPosition> board.boardSize)
            return false;
        if(newPosition == 4|| newPosition == 5||newPosition ==2)
        {
            System.out.println("There Is a ladder");

        } else if (newPosition == 11 || newPosition ==12|| newPosition == 13) {
            System.out.println("There is a snake");
        }

        Cells cell = board.getPoisition(newPosition);
        if (cell.type == CellType.TARGET) {
            return true;
         }
        else if(cell.type == CellType.SNAKE || cell.type == CellType.LADDER)
        {
            this.position = cell.end;
            System.out.println("Player "+name + " Move to "+cell.end);
        }
        else
        {
            position = newPosition;
            System.out.println("Player "+name + " Move to "+position);
        }
        return false;
    }
}
