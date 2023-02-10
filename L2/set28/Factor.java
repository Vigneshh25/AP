package set28;

import java.util.Arrays;

public class Factor {

        public static void sortByMinFactor(Integer[] array) {
            Arrays.sort(array, (a, b) -> minFactor(b) - minFactor(a));
    };

        public static int minFactor(int n) {
            for (int i = 2; i <= n / i; i++) {
                if (n % i == 0) {
                    return i;
                }
            }
            return n;
        }

        public static void main(String[] args) {
            Integer[] array = {12, 15, 21, 30, 7};
            sortByMinFactor(array);
            System.out.println(Arrays.toString(array)); // Output: [7, 15, 21, 30, 12]
        }
    }



