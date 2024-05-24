package Problems.set28;

import java.util.Arrays;

public class LexSort {
        public static void main(String[] args) {
            String[] words = {"cherry", "apple", "banana"};
            selectionSort(words);
            System.out.println(Arrays.toString(words));  // [apple, banana, cherry]
        }

        public static void selectionSort(String[] array) {
            for (int i = 0; i < array.length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j].compareTo(array[minIndex]) < 0) {
                        minIndex = j;
                    }
                }
                String temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }


}
