package Problems.set5;

public class LargestPrime {
    public static void main(String[] args) {
        int n = 100;
        int largestPrime = largestPrime(n);
        if (largestPrime == -1) {
            System.out.println("No prime number found.");
        } else {
            System.out.println("The largest prime number less than or equal to " + n + " is " + largestPrime);
        }
    }

    public static int largestPrime(int n) {
        while (n >= 2) {
            if (isPrime(n)) {
                return n;
            }
            n--;
        }
        return -1; // no prime number found
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
