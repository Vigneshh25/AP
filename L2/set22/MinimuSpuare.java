package set22;
import java.util.*;
import java.io.*;

package org.example.QueryEngine;

public class SumOfSquares {

    public static int minSquares(int n) {
        // base case: if n is already a perfect square, return 1
        int sqrt = (int) Math.sqrt(n);
        if (sqrt * sqrt == n) {
            return 1;
        }

        // find the minimum number of squares required by trying all possible combinations
        int min = n; // initialize to maximum possible value
        for (int i = 1; i <= sqrt; i++) {
            int squares = 1 + minSquares(n - i*i);
            if (squares < min) {
                min = squares;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int n = 100;
        System.out.println("Minimum number of squares required to represent " + n + " is " + minSquares(n));
    }
}
