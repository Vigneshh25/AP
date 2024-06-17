package Problems.set39;

import java.util.Arrays;
import java.util.Scanner;

class MatrixAddition
{
public static void main(String[] args) {
    // Input the number of arrays and initialize the sum to 0
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int sum = 0;
    
    // Loop through the n arrays
    for (int i = 0; i < n; i++) {
        int[] array = new int[scanner.nextInt()]; // input.txt the size of the array
        for (int j = 0; j < array.length; j++) {
            array[j] = scanner.nextInt(); // input.txt the elements of the array
        }
        sum += Arrays.stream(array).sum(); // add the sum of the array to the total sum
    }
    
    System.out.println(sum); // print the total sum
}
}
