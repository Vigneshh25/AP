package z;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Car_Parking {
    private static void move_to_exit(int n, int m, String [][]a, String number, int[] lower_parking_area, int[] empty_places){
        for(int i=1;i<n-1;i++){
            for(int j=1;j<m-1;j++){
                if(i == n/2) continue;
                if(a[i][j].equals(number)){
                    a[i][j] = "----";
                    if(i> n/2) lower_parking_area[0]++;
                    empty_places[0]++;
                    System.out.println("Car Exited from : ("+j+","+((n-1)-i)+")");
                    return;
                }
            }
        }
    }
    private static void park(String num, int n, int m, String[][] a, int[] lower_parking_area, int[] empty_places){
        if(lower_parking_area[0] > 0){
            for(int i=(n/2)+1;i<n-1;i++){
                for(int j=1;j<m-1;j++){
                    if(a[i][j].equals("----")){
                        a[i][j] = num;
                        lower_parking_area[0]--;
                        empty_places[0]--;
                        System.out.println("Car parked at : ("+j+","+((n-1)-i)+")");
                        return;
                    }
                }
            }
        }
        else{
            for(int i=1;i<n/2;i++){
                for(int j=1;j<m-1;j++){
                    if(a[i][j].equals("----")){
                        a[i][j] = num;
                        empty_places[0]--;
                        System.out.println("Car parked at : ("+j+","+((n-1)-i)+")");
                        return;
                    }
                }
            }
        }
    }
    private static void print(String[][] a){
        System.out.println("Parking Map : ");
        for(String[] row : a){
            for(String str : row) System.out.print(str+"    ");
            System.out.println();
            System.out.println();
        }
    }

    // *************
    // ***** MAIN METHOD *****
    // *************

    public static void main(String [] args){
        Set<String> vehicle_numbers = new HashSet<>();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Length & Width of Parking places :");
        int length = in.nextInt();
        int width = in.nextInt();
        if(length < 2||width < 2){
            System.out.println("Length or width is too small");
            return;
        }
        int[] total_empty_places = new int [1];
        total_empty_places[0] = length * width;
        int n = length + 3;
        int m = width + 2;
        int[] lower_parking_area = new int[1];
        lower_parking_area[0] = ((n-2) - (n/2)) * width;
        String[][] a = new String [n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i == 0||i == n-1||j == 0||j == m-1||i == n/2) a[i][j] = " X  ";
                else a[i][j] = "----";
            }
        }
        print(a);
        while(true) {
            System.out.print("1.Entry  2.Exit car  3.Print top view   4.Close : ");
            int choice = in.nextInt();
            switch (choice) {
                case 1: {
                    if (total_empty_places[0] == 0) {
                        System.out.println("!.....Parking Full.....!");
                        break;
                    }
                    System.out.print("Enter Car number : ");
                    String car_num = in.next();
                    if(car_num.length() != 4){
                        System.out.println("** Enter Valid vehicle number..! **");
                        break;
                    }
                    if (!vehicle_numbers.add(car_num)) {
                        System.out.println("** Same Vehicle number not allowed..! **");
                        break;
                    }
                    vehicle_numbers.add(car_num);
                    park(car_num , n, m, a , lower_parking_area , total_empty_places);
                    print(a);
                    break;
                }
                case 2 :{
                    System.out.print("Enter the Vehicle number : ");
                    String number = in.next();
                    if(!vehicle_numbers.contains(number)){
                        System.out.println("Vehicle number not found");
                        break;
                    }
                    vehicle_numbers.remove(number);
                    move_to_exit(n , m , a , number , lower_parking_area ,  total_empty_places);
                }
                case 3 :{
                    print(a);
                    break;
                }
                case 4 : System.exit(0);
            }
        }
    }
}