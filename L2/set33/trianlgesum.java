package set33;

public class trianlgesum {
    public static void main(String[] args) {

        int[][] mat = {{1, 2, 3, 4, 6}, {5, 3, 8, 1, 2}, {4, 6, 7, 5, 5}, {2, 4, 8, 9, 4}};
        int row1 = 2, col1 = 0, row2 = 3, col2 = 4;
        int sum = 0;

        System.out.println("Rectangle: ");
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                System.out.print(mat[i][j] + " ");
                sum += mat[i][j];
            }
            System.out.println();
        }

        System.out.println("Sum: " + sum);

    }
}
