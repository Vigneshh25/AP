package Problems.set13;

public class Pattern {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            int n = i;
            System.out.print(i+" ");
            for (int j = 1; j <i; j++) {
                n += 5;
                System.out.print(n + " ");

            }
            System.out.println();

        }
        System.out.println();
     int n =5;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i > j) {
                    System.out.print(j);
                } else {
                    System.out.print(i);
                }
            }
            System.out.println();
        }


    }
}

