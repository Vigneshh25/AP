package Problems.set32;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortOddEven {


        // Utility function to print
        // the contents of the array
        static void printArr(ArrayList<Integer> arr, int n) {
            for (int i = 0; i < n; i++)
                System.out.print(arr.get(i) + " ");
        }

        // Driver code
        public static void main(String args[]) {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            arr.add(1);
            arr.add(3);
            arr.add(2);
            arr.add(7);
            arr.add(5);
            arr.add(4);
            int n = arr.size();


            // Sort the array
            Collections.sort(arr, new Comparator<Integer>() {
                @Override
                public int compare(Integer a, Integer b) {

                    // If both numbers are even,
                    // smaller number should
                    // be placed at lower index
                    if (a % 2 == 0 && b % 2 == 0)
                        return (a - b);

                    // If both numbers are odd larger number
                    // should be placed at lower index
                    if (a % 2 != 0 && b % 2 != 0)
                        return (b - a);

                    // If a is odd and b is even,
                    // a should be placed before b
                    if (a % 2 != 0)
                        return -1;

                    // If b is odd and a is even,
                    // b should be placed before a
                    return 0;
                }
            });

            // Print the sorted array
            printArr(arr, n);
        }
    }

// This code is contributed by Saurabh Jaiswal

