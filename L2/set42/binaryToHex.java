package set32;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BinaryToHex {


        // Utility function to print
        // the contents of the array
        public static String binaryToHex(String binary) {
            String hex = "";
            String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
            int len = binary.length();
            // pad the binary string with zeros on the left to make it divisible by 4
            while (len % 4 != 0) {
                binary = "0" + binary;
                len++;
            }
            // convert each group of 4 bits to its hexadecimal equivalent
            for (int i = 0; i < len; i += 4) {
                String group = binary.substring(i, i+4);
                int decimal = Integer.parseInt(group, 2);
                hex += hexDigits[decimal];
            }
            return hex;
        }


    public static void main(String[] args) {
        System.out.println(binaryToHex("10101"));
    }

}

// This code is contributed by Saurabh Jaiswal

