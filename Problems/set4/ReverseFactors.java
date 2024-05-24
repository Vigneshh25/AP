package Problems.set4;

import java.util.Arrays;
import java.util.Comparator;

class ReverseFactors {
    public static int countFactors(int n) {
        int count = 0;
        for (int i = 1; i*i <= n; i++) {
            if (n % i == 0) {
               if(n/i==i)
               { System.out.print(" "+ i);
                   count++;
               }
                else
                {
                   count+=2;System.out.print(n/i+" "+ i);
                }
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
