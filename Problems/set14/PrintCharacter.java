package Problems.set14;

public class PrintCharacter {
    public static void convertToAlphabet(int columnNumber) {
        StringBuilder columnName = new StringBuilder();

        while (columnNumber > 0) {
            // Find remainder
            int rem = columnNumber % 26;

            // If remainder is 0, then a
            // 'Z' must be there in output
            if (rem == 0) {
                columnName.append("Z");
                columnNumber = (columnNumber / 26) - 1;
            }
            else // If remainder is non-zero
            {
                columnName.append((char)((rem - 1) + 'A'));
                columnNumber = columnNumber / 26;
            }
        }

        // Reverse the string and print result
        System.out.println(columnName.reverse());
    }
    public static void main(String[] args) {


        convertToAlphabet(1);  // Output: "A"
        convertToAlphabet(26); // Output: "Z"
        convertToAlphabet(27); // Output: "AA"
        convertToAlphabet(676); // Output: "ZZZ"

    }
}
