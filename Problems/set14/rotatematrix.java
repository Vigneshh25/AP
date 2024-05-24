package Problems.set14;

import java.util.Arrays;

public class rotatematrix {
    public static int[][] rotateMatrix(int[][] matrix, int degrees) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
        return matrix;
    }
    static void rotate90clockwise(int mat[][])
    {

        // Transpose of matrix
        for (int i = 0; i < mat.length; i++)
            for (int j = i + 1; j < mat[0].length; j++) {
                int t = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = t;
            }

        // Reverse individual rows
            for (int i = 0; i < mat.length; i++){
            int low = 0, high = mat.length - 1;
            while (low < high) {
                int t = mat[i][low];
                mat[i][low] = mat[i][high];
                mat[i][high] = t;
                low++;
                high--;
            }
        }
    }

    // Test the function
    public static void main(String[] args) {
        int matrix[][] = { { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        int[][] rotated = rotateMatrix(matrix, 90);
        System.out.println(Arrays.deepToString(rotated));
// Output: [[7, 4, 1], [8, 5, 2], [9, 6, 3]]

        rotated = rotateMatrix(matrix, 180);
        System.out.println(Arrays.deepToString(rotated));
// Output: [[9, 8, 7], [6, 5, 4], [3, 2, 1]]

        rotated = rotateMatrix(matrix, 270);
        System.out.println(Arrays.deepToString(rotated));
// Output: [[3, 6, 9], [2, 5, 8], [1, 4, 7]]

        rotated = rotateMatrix(matrix, 360);
        System.out.println(Arrays.deepToString(rotated));
// Output: [[1, 2, 3], [4, 5, 6], [7, 8, 9]]

    }

}
