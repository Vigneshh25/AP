package set2;

import java.util.Arrays;
import java.util.Collections;

public class sorting {
    public static int[] alternateSort(int[] arr) {
        // Sort the array in non-ascending order
        Arrays.sort(new int[][]{arr}, Collections.reverseOrder());

        // Initialize variables to traverse the array
        int i = 0;
        int j = arr.length - 1;

        // Initialize the result array
        int[] result = new int[arr.length];

        // Traverse the array
        int k = 0;
        while (i < j) {
            result[k] = arr[i];
            k++;
            i++;
            result[k] = arr[j];
            k++;
            j--;
        }

        // If i and j are equal, append the remaining value
        if (i == j) {
            result[k] = arr[i];
        }

        return result;
    }

    public static void main(String[] args) {
        // Test the function
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        int[] arr2 = {7, 6, 5, 4, 3, 2, 1};
        int[] arr3 = {1, 3, 5, 2, 4, 6};
        System.out.println(Arrays.toString(alternateSort(arr1)));  // should print [7, 1, 6, 2, 5, 3, 4]
        System.out.println(Arrays.toString(alternateSort(arr2)));  // should print [7, 1, 6, 2, 5, 3, 4]
        System.out.println(Arrays.toString(alternateSort(arr3)));  // should print [6, 1, 5, 2, 4, 3]
    }
}
