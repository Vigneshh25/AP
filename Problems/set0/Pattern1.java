package Problems.set0;

class Pattern1 {
    private static void solve(int n) {
        for (int i = 0; i < n; i++) System.out.print("  *");
        System.out.println();
        for (int i = 0; i < n * 4; i++) {
            if (i % 4 == 0) System.out.print(" ");
            else System.out.print("*");
        }
        System.out.println();
        for (int i = 0; i < n * 5; i++) System.out.print("*");
    }

    public static void main(String[] args) {
        solve(4);
    }
}
