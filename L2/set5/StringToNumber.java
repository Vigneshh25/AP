package set5;

public class StringToNumber {

        static String convertToString(int n) {
            // Initialize an empty string for the result
            String result = "";

            // Convert the integer to base 26
            while (n > 0) {
                // Append the corresponding letter of the alphabet to the result string
                result = (char)((n % 26) + 'A' - 1) + result;
                // Divide the integer by 26 and round down to the nearest integer
                n = n / 26;
            }

            return result.replace("@","z");
        }

        public static void main(String[] args) {
            // Test the function
            System.out.println(convertToString(1));  // Output: "A"
            System.out.println(convertToString(26));  // Output: "Z"
            System.out.println(convertToString(20));  // Output: "AA"
            System.out.println(convertToString(28));  // Output: "AB"
            System.out.println(convertToString(1000));  // Output: "ALL"

    }

}

