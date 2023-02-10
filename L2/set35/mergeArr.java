package set35;

import java.util.Arrays;

public class mergeArr {
    public static void mergeArrays(int[] a, int[] b) {
        // Initialize i and j to 0
        int i = 0, j = 0;
        // Initialize an empty array to store the merged array
        int[] output = new int[a.length + b.length];
        // Merge the two arrays
        int k = 0;
        while (i < a.length || j < b.length) {
            // If i is less than the length of the first array and j is less than the length of the second array, compare the elements at the current indices of the two arrays
            if (i < a.length && j < b.length) {
                // If the element in the first array is smaller, add it to the output array and increment i by one
                if (a[i] < b[j]) {
                    output[k++] = a[i++];
                }
                // If the element in the second array is smaller, add it to the output array and increment j by one
                else if (a[i] > b[j]) {
                    output[k++] = b[j++];
                }
                // If the elements are equal, add the element from the first array to the output array, increment i by one, and set j to the index of the next occurrence of the element in the second array
                else {
                    output[k++] = a[i++];
                    j = findNext(b, j, a[i - 1]);
                }
            }
            // If i is equal to the length of the first array, append the remaining elements of the second array (if any) to the output array
            else if (i == a.length) {
                while (j < b.length) {
                    output[k++] = b[j++];
                }
            }
            // If j is equal to the length of the second array, append the remaining elements of the first array (if any) to the output array
            else if (j == b.length) {
                while (i < a.length) {
                    output[k++] = a[i++];
                }
            }
        }
        // Return the output array
        System.out.println(Arrays.toString(output));
    }

    // Helper method to find the index of the next occurrence of a given element in an array
    public static int findNext(int[] array, int start, int element) {
        for (int i = start; i < array.length; i++) {
            if (array[i] != element) {
                return i;
            }
        }
        return array.length;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 4, 5, 6 };
        int [] b = new int[]{2,4,6,8};
        mergeArrays(a,b);


    }
}
