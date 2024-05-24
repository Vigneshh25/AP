package Problems.set32;

import java.util.Arrays;

public class Anagram {
    public static void main(String[] args) {
        String s = "cat";
        String t = "atc";
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        System.out.println(Arrays.equals(sChars, tChars));
    }
    }

