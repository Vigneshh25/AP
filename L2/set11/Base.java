package set11;

public class Base {
        public static void main(String[] args) {
            String a = "123";
            String b = "13";
            int n = 4;
            String result = addBaseNNumbers(a, b, n);
            System.out.println(result);  // Expected output: "200"
        }

        public static String addBaseNNumbers(String a, String b, int n) {
            String result = "";
            int carry = 0;
            int i = 0;
            while (i < a.length() || i < b.length()) {
                int aDigit = i < a.length() ? Character.getNumericValue(a.charAt(a.length() - i - 1)) : 0;
                int bDigit = i < b.length() ? Character.getNumericValue(b.charAt(b.length() - i - 1)) : 0;
                int sum = aDigit + bDigit + carry;
                carry = sum / n;
                result = (sum % n) + result;
                i++;
            }
            if (carry == 1) {
                result = "1" + result;
            }
            return result;
        }

}
