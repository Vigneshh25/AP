package Problems.set26;

class MatrixPath {
    private static final int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // Possible directions to move in the matrix
    private static boolean[][] visited; // Matrix to keep track of visited cells
    private static int[][] path; // Matrix to store the path

    public static boolean findPath(int[][] matrix, int row, int col) {
        // Check if we have reached the ending point
        if (row == matrix.length - 1 && col == matrix[0].length - 1) {
            path[row][col] = 1; // Mark the ending point in the path
            return true;
        }

        // Mark t

        visited[row][col]= true;

// Try moving in each possible direction
        for (int[] dir : DIRS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < matrix.length && newCol >= 0 && newCol < matrix[0].length && matrix[newRow][newCol] == 1 && !visited[newRow][newCol]) {
                // If the new cell is a valid non-block and has not been visited yet, recursively search from there
                if (findPath(matrix, newRow, newCol)) {
                    path[row][col] = 1; // Mark the current cell in the path
                    return true;
                }
            }
        }

// If no path was found, return false
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 0, 0}, {1, 0, 0, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};
        visited = new boolean[matrix.length][matrix[0].length];
        path = new int[matrix.length][matrix[0].length];
        boolean found = findPath(matrix, 0, 0);
        if (found) {
            // If a path was found, print it
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (path[i][j] == 1) {
                        System.out.print("_ ");
                    } else {
                        System.out.print(matrix[i][j] + " ");
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("No path found.");
        }
    }
}

