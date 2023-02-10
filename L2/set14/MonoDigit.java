package set14;

public class MonoDigit {
    public static String convertToMonoDigit(int num) {
        String result = Integer.toString(num);
        for (int i = 0; i < result.length() - 1; i++) {
            int digit1 = Character.getNumericValue(result.charAt(i));
            int digit2 = Character.getNumericValue(result.charAt(i + 1));
            if (digit1 + digit2 < 10) {
                result = result.substring(0, i) + (digit1 + digit2) + result.substring(i + 2);
            } else if (digit1 - digit2 >= 0) {
                result = result.substring(0, i) + (digit1 - digit2) + result.substring(i + 2);
            } else {
                break;
            }
        }
        if (result.length() == 1) {
            return result;
        } else {
            return "cannot create a mono digit number";
        }
    }
                public static void main(String[] args) {
                    System.out.println(convertToMonoDigit( 72581 ));
    }
}
