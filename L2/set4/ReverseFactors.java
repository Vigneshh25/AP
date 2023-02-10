package set4;

import java.util.Arrays;
import java.util.Comparator;

class ReverseFactors {
    public static int countFactors(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Integer[] array = {8, 2, 3, 12, 16};
        Arrays.sort(array, (a, b) -> countFactors(b) - countFactors(a));
        System.out.println(Arrays.toString(array));
    }
}
