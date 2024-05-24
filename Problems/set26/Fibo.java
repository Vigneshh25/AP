package Problems.set26;

import java.util.ArrayList;
import java.util.List;

public class Fibo {

        public static void main(String[] args) {
            // The series of numbers
            int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

            // Generate the Fibonacci numbers
            int a = 0;
            int b = 1;
            List<Integer> fibonacci = new ArrayList<>();
            fibonacci.add(a);
            fibonacci.add(b);
            while (b < 100) {
                int c = a + b;
                fibonacci.add(c);
                a = b;
                b = c;
            }

            // Print only the numbers that are present in the Fibonacci series
            for (int number : numbers) {
                if (fibonacci.contains(number)) {
                    System.out.println(number);
                }
            }
        }

}

