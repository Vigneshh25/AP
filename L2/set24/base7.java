package set24;

public class base7 {
    // Java program to Find Number
// of digits in base b.

    
    public static void findNumberOfDigits(long n, int base) {
        int dig = 0;
        while (n > 0) {
            n /= base;
            dig++;
        }
        System.out.println("The Number of digits of Number " + n + " in base " + base + " is " + dig);
    }
 
    public static void main(String[] args) {
        long n = 1446;
        int base = 7;
        findNumberOfDigits(n, base);
    }
}

