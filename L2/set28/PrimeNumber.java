package set28;

public class PrimeNumber {
    public static void printPrimeNumbers(int n) {
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        printPrimeNumbers(10); // Output: 2 3 5 7 11 13 17 19 23 29
    }
}


