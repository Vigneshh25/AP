package set37;

public class Prime {
    public static void main(String[] args) {
        if (isprime(9)) {
            System.out.println(1);
        } else
            System.out.println(0);
    }

    static boolean isprime(long n) {
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

    int isTwistedPrime(int N) {
        int rev = 0;
        int number = N;
        while (N > 0) {
            int rem = N % 10;
            rev = rev * 10 + rem;
            N = N / 10;
        }
        if (isprime(number) && isprime(rev)) {
            return 1;
        } else {
            return 0;
        }
    }
}