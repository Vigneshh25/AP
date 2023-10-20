package org.example.QueryEngine;

import java.util.Arrays;
import java.util.Comparator;

public class NumberSorter {

    public static void main(String[] args) {
        Integer[] numbers = {100, 1, 11, 21, 2, 3};
        System.out.println("Before sorting: " + Arrays.toString(numbers));
        sortNumbers(numbers);
        System.out.println("After sorting: " + Arrays.toString(numbers));
    }

    public static void sortNumbers(Integer[] numbers) {
        Arrays.sort(numbers, (o1, o2) -> {
            String s1 = o1.toString();
            String s2 = o2.toString();
            int i = 0;
            while (i < s1.length() && i < s2.length()) {
                int cmp = s2.charAt(i) - s1.charAt(i);
                if (cmp != 0) {
                    return s1.charAt(i)-s2.charAt(i);
                }
                i++;
            }
            return s1.length() - s2.length();
        });
    }
}
