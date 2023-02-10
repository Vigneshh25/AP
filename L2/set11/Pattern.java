package set11;

public class Pattern {

        public static void main(String[] args) {
            int n = 6;
            printPattern(n);
        }

        public static void printPattern(int n) {
            int current = 1;
            for (int i = 1; i < n * 2; i++) {
                if (i <= n) {
                    System.out.println(current);
                    current++;
                } else {
                    System.out.print(current + " ");
                    for (int j = current + 1; j < current + n; j++) {
                        System.out.print(j + " ");
                    }
                    System.out.println();
                    current += n;
                }
            }

    }

}
