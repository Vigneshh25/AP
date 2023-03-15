public class DecimalTobin {
    public static void main(String[] args) {
        int decimalValue = 17;
        String binaryString = "";

        while (decimalValue > 0) {
            binaryString = (decimalValue % 2) + binaryString;
            decimalValue /= 2;
        }

        System.out.println(binaryString);  // Output: 1101

    }
}
