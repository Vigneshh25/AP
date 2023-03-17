package com.example.demo1;

import java.util.Arrays;

public class StringRotation {

    public static boolean areRotations(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        String s1s1 = s1 + s1;
        return s1s1.contains(s2);
    }
    public static void main(String[] args) {
        String s1 = "hello from here";
        String s2 = "rehello from he";
        boolean result = areRotations(s1, s2);
        System.out.println(result);

    }
}
