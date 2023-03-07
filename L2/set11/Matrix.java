package set11;

public class Matrix {
    public static void main(String[] args) {
        int[][] big = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] small = {{6, 7}, {10, 11}};
        boolean result = isSubmatrix(big, small);
        System.out.println(result);  // Expected output: true


        System.out.println(result);  // Expected output: false
    }

    public static boolean isSubmatrix(int[][] matrix, int[][] submatrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int subRows = submatrix.length;
        int subCols = submatrix[0].length;

        for (int i = 0; i <= rows - subRows; i++) {
            for (int j = 0; j <= cols - subCols; j++) {
                boolean found = true;
                for (int k = 0; k < subRows; k++) {
                    for (int l = 0; l < subCols; l++) {
                        if (matrix[i + k][j + l] != submatrix[k][l]) {
                            found = false;
                            break;
                        }
                    }
                    if (!found) {
                        break;
                    }
                }
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }
}
