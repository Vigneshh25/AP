package set11;

public class Matrix {
        public static void main(String[] args) {
            int[][] big = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
            int[][] small = {{6, 7}, {10, 11}};
            boolean result = isSubmatrix(big, small);
            System.out.println(result);  // Expected output: true


            System.out.println(result);  // Expected output: false
        }

        public static boolean isSubmatrix(int[][] big, int[][] small) {
            int n = big.length;
            int m = small.length;
            for (int i = 0; i < n - m + 1; i++) {
                for (int j = 0; j < n - m + 1; j++) {
                    boolean found = true;
                    for (int p = 0; p < m; p++) {
                        for (int q = 0; q < m; q++) {
                            if (big[i + p][j + q] != small[p][q]) {
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
