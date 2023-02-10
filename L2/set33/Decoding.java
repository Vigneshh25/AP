package set33;

public class Decoding{
    public static void main(String[] args) {
        String digits = "1234";
        int n = digits.length();

// dp[i] will store the number of decodings for the first i digits
        int[] dp = new int[n + 1];

// base cases
        dp[0] = 1; // an empty string can be decoded in 1 way (empty string)
        dp[1] = 1; // a string with one digit can be decoded in 1 way (single digit)

        for (int i = 2; i <= n; i++) {
            // if the current digit is non-zero, it can be used as a single digit
            if (digits.charAt(i - 1) > '0') {
                dp[i] += dp[i - 1];
            }

            // if the last two digits form a valid two-digit number, they can be used as a pair
            int twoDigits = Integer.parseInt(digits.substring(i - 2, i));
            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        System.out.println("Number of decodings: " + dp[n]);// The possible decodings are “ABA”, “AU”, “LA”

    }
}
