class PatternPrinting {
    public static void main(String[] args) {
        char[][] pattern = new char[5][5];
        int rows = 5, columns = 5;
        int rowStart = 0, rowEnd = rows - 1;
        int colStart = 0, colEnd = columns - 1;
        char currentChar = 'X';

        while (rowStart <= rowEnd && colStart <= colEnd) {
            // Fill the top row
            for (int i = colStart; i <= colEnd; i++) {
                pattern[rowStart][i] = currentChar;
            }
            rowStart++;

            // Fill the rightmost column
            for (int i = rowStart; i <= rowEnd; i++) {
                pattern[i][colEnd] = currentChar;
            }
            colEnd--;

            // Fill the bottom row, if there are remaining rows
            if (rowStart <= rowEnd) {
                for (int i = colEnd; i >= colStart; i--) {
                    pattern[rowEnd][i] = currentChar;
                }
                rowEnd--;
            }

            // Fill the leftmost column, if there are remaining columns
            if (colStart <= colEnd) {
                for (int i = rowEnd; i >= rowStart; i--) {
                    pattern[i][colStart] = currentChar;
                }
                colStart++;
            }

            // Toggle the character (X to O or O to X)
            currentChar = (currentChar == 'X') ? 'O' : 'X';
        }

        // Print the pattern
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(pattern[i][j] + " ");
            }
            System.out.println();
        }
    }
}
