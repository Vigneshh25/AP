package Problems.set11;


public class Binarynum {
    public static void main(String[] args) {
        String a = "1010";
        String b = "11001";
        String result = addBinaryNumbers(a, b);
        System.out.println(result);  // Expected output: "100011"
    }

    public static String addBinaryNumbers(String a, String b) {
        String result = "";
        int carry = 0;
        int i = 0;
        while (i < a.length() || i < b.length()) {
            int aDigit = i < a.length() ? Character.getNumericValue(a.charAt(a.length() - i - 1)) : 0;
            int bDigit = i < b.length() ? Character.getNumericValue(b.charAt(b.length() - i - 1)) : 0;
            int sum = aDigit + bDigit + carry;
            carry = sum / 2;
            result = (sum % 2) + result;
            i++;
        }
        if (carry == 1) {
            result = "1" + result;
        }
        return result;
    }
}
