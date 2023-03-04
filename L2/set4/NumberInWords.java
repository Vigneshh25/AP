package set4;



class NumbersInWords {
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

        int lakhs = n / 100000;
        int thousands = (n / 1000) % 100;
        int hundreds = (n / 100) % 10;
        int tens = (n % 100) / 10;
        int units = n % 10;
        String s = "";

        if (lakhs > 0) {
            s += toWords(lakhs) + " lakh ";
        }

        if (thousands > 0) {
            s += toWords(thousands) + " thousand ";
        }

        if (hundreds > 0) {
            s += NUMBERS[hundreds] + " hundred ";
        }

        if (tens > 0) {
            if (tens == 1) {
                s += NUMBERS[10 + units] + " ";
            } else {
                s += NUMBERS[20 + tens - 2] + " ";
                if (units > 0) {
                    s += NUMBERS[units] + " ";
                }
            }
        } else if (units > 0) {
            s += NUMBERS[units] + " ";
        }

        return s.trim();
    }

    public static void main(String[] args) {
        System.out.println(toWords(123445));
        System.out.println(toWords(999999));
        System.out.println(toWords(456));

        System.out.println(toWords(0));
    }
}
