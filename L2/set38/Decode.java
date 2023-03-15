package set28;

import java.util.ArrayList;
import java.util.List;

public class NumberToString {
    private static final char[] MAPPING = {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static List<String> numberToString(String digits) {
        List<String> result = new ArrayList<>();
        generateStrings(digits, 0, "", result);
        return result;
    }

    private static void generateStrings(String digits, int index, String current, List<String> result) {
        if (index == digits.length()) {
            result.add(current);
            return;
        }

        int digit = digits.charAt(index) - '0';
        if (digit == 0) {
            return;
        }

        // map the digit to a character and add it to the current string
        char c = MAPPING[digit];
        generateStrings(digits, index + 1, current + c, result);

        // if there are at least two digits remaining, map the next two digits to a character and add it to the current string
        if (index < digits.length() - 1) {
            int nextDigit = digits.charAt(index + 1) - '0';
            int value = digit * 10 + nextDigit;
            if (value <= 26) {
                c = MAPPING[value];
                generateStrings(digits, index + 2, current + c, result);
            }
        }
    }

    public static void main(String[] args) {
        String digits = "1123";
        List<String> strings = numberToString(digits);
        for (String s : strings) {
            System.out.println(s);
        }
    }
}
