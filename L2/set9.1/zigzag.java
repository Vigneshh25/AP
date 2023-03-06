public static void main(String[] args) {

        int matrix[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        int row = 0, col = 0;
        boolean isGoingRight = true;

        while (row < numRows) {
            System.out.print(matrix[row][col] + " ");
            if (isGoingRight) {
                if (col == numCols - 1) {
                    row++;
                    isGoingRight = false;
                } else if (row == 0) {
                    col++;
                    isGoingRight = false;
                } else {
                    row--;
                    col++;
                }
            } else {
                if (row == numRows - 1) {
                    col++;
                    isGoingRight = true;
                } else if (col == 0) {
                    row++;
                    isGoingRight = true;
                } else {
                    row++;
                    col--;
                }
            }
        }
