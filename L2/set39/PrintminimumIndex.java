package set39;


import java.util.*;

public class PrintminimumIndex {
    public static String findRange(String s1, String s2) {
        int minIndex = Integer.MAX_VALUE;
        int maxIndex = Integer.MIN_VALUE;

        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            int index = s1.indexOf(c);
            if (index != -1) {
                minIndex = Math.min(minIndex, index);
                maxIndex = Math.max(maxIndex, index);
            }
        }

        return s1.substring(minIndex, maxIndex + 1);
    }

    public static void main(String[] args) {
        String s1 = "ZOHOCORPORATION";
        String s2 = "PORT";
        System.out.println(findRange(s1, s2)); // prints "OHOCORPORAT"

    }
}