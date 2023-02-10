package set21;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a 2 or 3 digit number: ");
        int n = sc.nextInt();
        sc.close();

        int numIterations = 0;
        while (numIterations < 5) {
            int reversed = reverse(n);
            int sum = n + reversed;
            if (isPalindrome(sum)) {
                System.out.println(sum + " is a palindrome.");
                break;
            } else {
                n = sum;
                numIterations++;
            }
        }
    }

    public static int reverse(int n) {
        int reversed = 0;
        while (n > 0) {
            reversed = reversed * 10 + n % 10;
            n /= 10;
        }
        return reversed;
    }

    public static boolean isPalindrome(int n) {
        int reversed = reverse(n);
        return n == reversed;
    }
}


