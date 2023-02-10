package set23;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Duplicate {

        public static void main(String[] args) {
            int[] numbers = {1, 2,1, 3, 3, 4, 5};
            printWithoutDuplication(numbers);  // Output: 1 2 3 4 5
        }

        public static void printWithoutDuplication(int[] numbers) {
            Arrays.sort(numbers);
            int j=0;
            for(int i=0;i<numbers.length-1;i++)
            {
                if(numbers[i]!=numbers[i+1])
                    System.out.println(numbers[i]);
            }
//            numbers[j++] = numbers[numbers.length-1];
            System.out.println(numbers[6]);
        }

}
