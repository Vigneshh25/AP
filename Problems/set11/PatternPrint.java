package Problems.set11;

public class PatternPrint {
    public static void main(String[] args) {
        int n = 6;

        for (int i = 1; i <= n; i++) {
            int current = i;
            for (int j = 0; j < n-i; j++) {
                System.out.print(current+ "\t");
                current +=n-j;
            }
            System.out.println();
        }
    }
}
