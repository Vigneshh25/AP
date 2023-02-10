package set9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maximum {
        public static void main(String[] args) {
            int[] array = {3, 2, 7, 6, 5, 1, 2, 3, 4};
            int k = 3;
            printMax(array, k);
        }
    List<Integer> a = new ArrayList<>();
        public static void printMax(int[] array, int k) {
            for (int i = 0; i <= array.length - k; i++) {
                int[] window = Arrays.copyOfRange(array, i, i + k);
                int max = Arrays.stream(window).max().getAsInt();
                System.out.println(max);
            }
        }

}
