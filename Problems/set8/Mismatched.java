package Problems.set8;

import java.util.Scanner;

public class Mismatched {

    public static void main(String[] args) {

        int n = 9;
        char[] arr1 = new char[]{'a','b','c','d','e','f','g','h','i'};
        char[] arr2 = new char[]{'a','b','d','e','e','g','g','i','i'};;


        for(int i=0;i<n;i++) {
            if(arr1[i]!=arr2[i]) {
                System.out.print(arr1[i]+""+arr2[i]+" ");
            }

        }
    }
}