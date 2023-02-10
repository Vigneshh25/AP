package set39;

public class path {
    public static int numPaths(int[][] matrix, int startRow, int startCol, int endRow, int endCol) {

        if (startRow == matrix.length-1 && startCol == matrix[0].length-1) {
            return 1;
        }

        if(startRow<0 || startCol<0 || startRow>=endRow || startCol==endCol || matrix[startRow][startCol]==0 ||matrix[startRow][startCol]==-1)
            return 0;

        int c =matrix[startRow][startCol];
        matrix[startRow][startCol] = -1;


        int numPaths = numPaths(matrix, startRow - 1, startCol, endRow, endCol)+ numPaths(matrix, startRow + 1, startCol, endRow, endCol) // move down
       + numPaths(matrix, startRow, startCol - 1, endRow, endCol)
        + numPaths(matrix, startRow, startCol + 1, endRow, endCol);

        matrix[startRow][startCol] = c;
        return numPaths;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 1}
        };

        int startRow = 0;
        int startCol =0 ;
        int endRow = matrix.length;
        int endCol = matrix[0].length;

        System.out.println(numPaths(matrix, startRow, startCol, endRow, endCol)); // prints 2

    }
}
