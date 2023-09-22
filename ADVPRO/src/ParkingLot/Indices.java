package ParkingLot;

public class Indices {
    int row,col;
    public Indices(int row,int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Indices{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}