package set4;

class numbersinwords {
    public static final String[] NUMBERS = {
            "zero", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
            "twenty", "thirty", "forty", "fifty", "sixty",
            "seventy", "eighty", "ninety"
    };

    public static String toWords(int n) {
        if (n == 0) {
            return NUMBERS[0];
        }
        int thousands = n / 1000;
        int hundreds = (n % 1000) / 100;
        int tens = (n % 100) / 10;
        int units = n % 10;
        String s = "";
        if (thousands > 0) {
            s += NUMBERS[thousands] + " thousand and ";
        }
        if (hundreds > 0) {
            s += NUMBERS[hundreds] + " hundred and ";
        }
        if (tens > 0) {
            if (tens == 1) {
                s += NUMBERS[10 + units] + " ";
            } else {
                s += NUMBERS[20 + tens - 2] + " ";
                if (units > 0) {
                    s += "and " + NUMBERS[units] + " ";
                }
            }
        } else if (units > 0) {
            s += NUMBERS[units] + " ";
        }
        return s.trim();
    }

    public static void main(String[] args) {
        System.out.println(toWords(234));
        System.out.println(toWords(1234));
        System.out.println(toWords(12345));
    }
}
