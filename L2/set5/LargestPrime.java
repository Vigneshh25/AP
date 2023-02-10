package set5;

public class LargestPrime {
    public static void main(String[] args) {
        int n = 5;
        int x = 4691;
        int max = (int) Math.pow(10, n) - 1;
        int largestPrime = 0;

        // check each number in the range for primality
        for (int i = max; i >= (int) Math.pow(10, n - 1); i--) {
            if (isPrime(i) && i < x) {
                largestPrime = i;
                break;
            }
        }

        System.out.println("Largest prime: " + largestPrime);
    }

    // function to check if a number is prime
    public static boolean isPrime(int n) {
        if (n <= 1) {
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
