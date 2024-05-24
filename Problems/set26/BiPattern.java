package Problems.set26;

public class BiPattern
{
    public static void printPattern(int n) {
        for (int i = 0; i < (int) Math.pow(2, n); i++) {
            // Iterate over the bits of i, from most significant to least significant
            for (int j = n-1; j >= 0; j--) {
                // Print 1 if the jth bit of i is 1, or 0 if it is 0
                System.out.print((i >> j) & 1 );
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printPattern(3);
    }
}


