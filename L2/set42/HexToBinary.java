package set32;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HexToBinary {


        // Utility function to print
        // the contents of the array
        public static String hexToBinary(String hex) {
            String binary = "";
            String[] hexDigits = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111",
                    "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
            for (int i = 0; i < hex.length(); i++) {
                char c = hex.charAt(i);
                if (c >= '0' && c <= '9') {
                    binary += hexDigits[c - '0'];
                } else if (c >= 'A' && c <= 'F') {
                    binary += hexDigits[c - 'A' + 10];
                } else if (c >= 'a' && c <= 'f') {
                    binary += hexDigits[c - 'a' + 10];
                } else {
                    throw new IllegalArgumentException("Invalid hexadecimal digit: " + c);
                }
            }
            return binary;
        }

    public static void main(String[] args) {
        System.out.println(hexToBinary("1AC5"));
    }

}

// This code is contributed by Saurabh Jaiswal

