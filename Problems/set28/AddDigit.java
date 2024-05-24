package Problems.set28;

public class AddDigit {
    public static String addDigit(int digit, int number) {
        String str = Integer.toString(number);
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append(Character.getNumericValue(c) + digit);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int digit = 4;
        int number = 2875;
        System.out.println(addDigit(digit, number)); // Output: 612119
    }
}
