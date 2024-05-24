package Problems.set24;

import java.util.Arrays;

public class insertarray {
 
        // Driver code
        public static void main(String[] args)
        {

            int n = 10;
            int i;

            // initial array of size 10
            int arr1[]
                    = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

            int[] arr = {1, 2, 4, 5}; // original array
            int element = 3; // element to insert
            int index = 2; // index where element will be inserted

            // shift elements to the right from the index
            for (int j = arr.length - 1; j > index; j--) {
                arr[j] = arr[j - 1];
            }

            // insert the new element
            arr[index] = element;

            // print the updated array
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[j] + " ");
            }

          

    }

}
