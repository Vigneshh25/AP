package ParkingLot;
import java.sql.SQLOutput;
import java.util.*;

public class Car {
    int length,width;
    int[][] grid ;
    ArrayList<Indices> parkingPlaces ;
    Map<Integer,Indices> carsParked ;

    public Car(int length,int width){
        this.length = length;
        this.width = width;
        grid = new int[length][width];
        parkingPlaces = new ArrayList<>();
        carsParked = new HashMap<>();
    }

    public void addParkingPlaces(){
        for (int i = 1; i < width-1; i ++){
            for (int j = 1; j < length-1; j ++){
                if (i == (width/2)){
                    break;
                }
                parkingPlaces.add(new Indices(i,j));
            }
        }
    }

    public void entry(int carNumber){

        if (parkingPlaces.isEmpty()) {
            System.out.println("Parking Full");
            return;
        }
        Indices indices = parkingPlaces.remove(0);
        System.out.println(indices);
        int row = indices.row;
        int col = indices.col;
        grid[row][col] = carNumber;
        carsParked.put(carNumber,new Indices(row,col));
        System.out.printf("Car parked at (%d,%d)\n",col,row);
    }

    public void exit(int carNumber){
        if (carsParked.size() == 0 || !(carsParked.containsKey(carNumber))){
            System.out.println("Invalid Vehicle Number");
            return;
        }

        Indices indices = carsParked.get(carNumber);
        int row = indices.row;
        int col = indices.col;

        grid[row][col] = 0;
        parkingPlaces.add(new Indices(row,col));
        carsParked.remove(carNumber);

        System.out.println("Car exited from the parking area");
    }

    public void printFloorMap(){
        for (int i = 0; i < width; i ++){
            for (int j = 0; j < length; j ++){
                if (i == 0  ||i == (width-1) || j == 0  ||j == (length-1) || i == (width/2)){
                    System.out.print("X        ");
                }else if (grid[i][j] > 0) {
                    System.out.print(String.valueOf(grid[i][j])+"     ");
                } else {
                    System.out.print("-        ");
                }
            }
            System.out.println();
        }
    }
}