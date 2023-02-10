package set38;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Decode {
    public static boolean
    nonLower(String s)
    {
        // Traverse the string
        for (int i = 0; i < s.length(); i++) {

            // If any character is not
            // found to be in lowerCase
            if (!Character
                    .isLowerCase(s.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    // Function to print the decodings
    public static void
    printCodes(String output[])
    {
        for (int i = 0; i < output.length; i++) {

            // If all characters are not
            // in lowercase
            if (nonLower(output[i]))
                continue;
            System.out.println(output[i]);
        }
    }

    // Function to return the character
    // corresponding to given integer
    public static char getChar(int n)
    {
        return (char)(n + 96);
    }

    // Function to return the decodings
    public static String[] getCode(
            String str)
    {
        // Base case
        if (str.length() == 0) {

            String ans[] = { "" };
            return ans;
        }

        // Recursive call
        String output1[]
                = getCode(str.substring(1));

        // Stores the characters of
        // two digit numbers
        String output2[] = new String[0];

        // Extract first digit and
        // first two digits
        int firstDigit
                = (str.charAt(0) - '0');
        int firstTwoDigit = 0;

        if (str.length() >= 2) {

            firstTwoDigit
                    = (str.charAt(0) - '0') * 10
                    + (str.charAt(1) - '0');

            // Check if it lies in the
            // range of alphabets
            if (firstTwoDigit >= 10
                    && firstTwoDigit <= 26) {

                // Next recursive call
                output2
                        = getCode(str.substring(2));
            }
        }

        // Combine both the output in a
        // single final output array
        String output[]
                = new String[output1.length
                + output2.length];

        // Index of final output array
        int k = 0;

        // Store the elements of output1
        // in final output array
        for (int i = 0; i < output1.length; i++) {

            char ch = getChar(firstDigit);

            output[i] = ch + output1[i];
            k++;
        }

        // Store the elements of output2
        // in final output array
        for (int i = 0; i < output2.length; i++) {

            char ch = getChar(firstTwoDigit);

            output[k] = ch + output2[i];
            k++;
        }

        // Result the result
        return output;
    }

    // Driver Code
    public static void main(String[] args)
    {
        String input = "1123";

        // Function call
        String output[] = getCode(input);

        // Print function call
        printCodes(output);
    }
}