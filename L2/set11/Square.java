package set11;

public class Square {
    public static void main(String[] args) {
        int a = 20;
        int b = 100;

        for (int i = a; i <= b; i++) {
            int sqrt = (int) Math.sqrt(i);

            if (sqrt * sqrt == i) {
                System.out.println(sqrt);
                System.out.println(i);
            }
        }

    }
}
