package set13;

public class count {
    public static void main(String[] args) {
        int n = 101;
        int count = 0;

        for (int i = 1; i <= n; i++) {
            count += String.valueOf(i).length();
        }

        System.out.println(count);

    }
}
