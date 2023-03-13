package set24;

import java.util.Arrays;

public class insertarray {
 
        // Driver code
        public static void main(String[] args)
        {

            int n = 10;
            int i;

            // initial array of size 10
            int arr[]
                    = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

            int[] arr = {1, 2, 4, 5}; // original array
            int element = 3; // element to insert
            int index = 2; // index where element will be inserted

            // shift elements to the right from the index
            for (int i = arr.length - 1; i > index; i--) {
                arr[i] = arr[i - 1];
            }

            // insert the new element
            arr[index] = element;

            // print the updated array
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }

          

    }

}
