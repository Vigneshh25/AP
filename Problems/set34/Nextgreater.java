package Problems.set34;

import java.util.Arrays;
import java.util.Stack;

public class Nextgreater {
    // Simple Java program to print next
// greater elements in a given array

        static void printNGE(int arr[], int n)
        {
            int next, i, j;
            for (i = 0; i < n; i++) {
                next = -1;
                for (j = i + 1; j < n; j++) {
                    if (arr[i] < arr[j]) {
                        next = arr[j];
                        break;
                    }
                }
                System.out.println(arr[i] + " -- " + next);
            }
        }

        public static void main(String args[])
        {
            int arr[] = {6, 3, 9, 10, 8, 2, 1, 15, 7};
            int n = arr.length;
            printNGE(arr, n);
        }
    }



