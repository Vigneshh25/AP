package set6;

public class MagicNumber {
    public static void main(String[] args) {
        int number = 124;
        boolean isMagic = isMagicNumber(number);
        System.out.println(isMagic);
    }
    public static boolean isMagicNumber(int number) {
        int cubed = number * number * number;
        while (number > 0) {
            int digit = number % 10;
            if (cubed % 10 != digit) {
                return false;
            }
            number /= 10;
            cubed /= 10;
        }
        return true;
    }
}
