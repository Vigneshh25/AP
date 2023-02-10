package set21;

import java.util.Scanner;

public class PrimeSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sc.nextInt();
        sc.close();

        if (isSumOfTwoPrimes(n)) {
            System.out.println(n + " can be expressed as the sum of two prime numbers.");
        } else {
            System.out.println(n + " cannot be expressed as the sum of two prime numbers.");
        }
    }

    public static boolean isSumOfTwoPrimes(int n) {
        for (int i = 2; i <= n / 2; i++) {
            if (isPrime(i) && isPrime(n - i)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}


