package Problems.game.suduko;

public class SudokuSolver {
    
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0)
            return;
        solve(board);
    }
    
    private boolean solve(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '0') {
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[row][col] = '0'; // backtrack
                            }
                        }
                    }
                    return false; // if no valid number can be placed
                }
            }
        }
        return true; // if all cells are filled
    }
    
    private boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            // Check row and column
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
            // Check 3x3 box
            int boxRowStart = (row / 3) * 3;
            int boxColStart = (col / 3) * 3;
            if (board[boxRowStart + i / 3][boxColStart + i % 3] == num) {
                return false;
            }
        }
        return true;
    }
    
    // Utility function to print Sudoku board
    public void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();
        char[][] board = {
            {'5', '3', '0', '0', '7', '0', '0', '0', '0'},
            {'6', '0', '0', '1', '9', '5', '0', '0', '0'},
            {'0', '9', '8', '0', '0', '0', '0', '6', '0'},
            {'8', '0', '0', '0', '6', '0', '0', '0', '3'},
            {'4', '0', '0', '8', '0', '3', '0', '0', '1'},
            {'7', '0', '0', '0', '2', '0', '0', '0', '6'},
            {'0', '6', '0', '0', '0', '0', '2', '8', '0'},
            {'0', '0', '0', '4', '1', '9', '0', '0', '5'},
            {'0', '0', '0', '0', '8', '0', '0', '7', '9'}
        };
        
        System.out.println("Original Sudoku:");
        solver.printBoard(board);
        
        solver.solveSudoku(board);
        
        System.out.println("\nSolved Sudoku:");
        solver.printBoard(board);
    }
}
