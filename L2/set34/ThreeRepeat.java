package set34;

import java.security.KeyStore;
import java.util.*;

public class ThreeRepeat {



    // User defined Pair class
    static class Pair {
        int first, second;
    }



        // Function to print top three repeated numbers
        static void top3Repeated(int[] arr, int n)
        {
            // There should be atleast two elements
            if (n < 3) {
                System.out.print("Invalid Input");
                return;
            }

            // Count Frequency of each element
            TreeMap<Integer, Integer> freq = new TreeMap<>();
            for (int i = 0; i < n; i++)
                if (freq.containsKey(arr[i]))
                    freq.put(arr[i], 1 + freq.get(arr[i]));
                else
                    freq.put(arr[i], 1);

            // Initialize first value of each variable
            // of Pair type is INT_MIN
            Pair x = new Pair();
            Pair y = new Pair();
            Pair z = new Pair();
            x.first = y.first = z.first = Integer.MIN_VALUE;

            for (Map.Entry curr : freq.entrySet()) {
                // If frequency of current element
                // is not zero and greater than
                // frequency of first largest element
                if (Integer.parseInt(String.valueOf(curr.getValue())) > x.first) {

                    // Update second and third largest
                    z.first = y.first;
                    z.second = y.second;
                    y.first = x.first;
                    y.second = x.second;

                    // Modify values of x Number
                    x.first = Integer.parseInt(String.valueOf(curr.getValue()));
                    x.second = Integer.parseInt(String.valueOf(curr.getKey()));
                }

                // If frequency of current element is
                // not zero and frequency of current
                // element is less than frequency of
                // first largest element, but greater
                // than y element
                else if (Integer.parseInt(String.valueOf(curr.getValue())) > y.first) {
                    // Modify values of third largest
                    z.first = y.first;
                    z.second = y.second;

                    // Modify values of second largest
                    y.first = Integer.parseInt(String.valueOf(curr.getValue()));
                    y.second = Integer.parseInt(String.valueOf(curr.getKey()));
                }

                // If frequency of current element
                // is not zero and frequency of
                // current element is less than
                // frequency of first element and
                // second largest, but greater than
                // third largest.
                else if (Integer.parseInt(String.valueOf(curr.getValue())) > z.first) {

                    // Modify values of z Number
                    z.first = Integer.parseInt(String.valueOf(curr.getValue()));
                    z.second = Integer.parseInt(String.valueOf(curr.getKey()));
                }
            }

            System.out.print("Three largest elements are " + x.second + " "
                    + y.second + " " + z.second);
        }

        // Driver's Code
        public static void main(String args[])
        {
            int[] arr = { 3, 4, 2, 3, 16, 3, 15,
                    16, 15, 15, 16, 2, 3 };
            int n = arr.length;
            top3Repeated(arr, n);
        }
    }

