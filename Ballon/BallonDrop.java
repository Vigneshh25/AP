package e;

import java.util.Scanner;

public class BalloonDrop {

    public static boolean checkForMatches(char[][] matrix) {
        boolean matches = false;
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i <= matrix.length - 3; i++) {
                if (matrix[i][j] == matrix[i + 1][j] && matrix[i + 1][j] == matrix[i + 2][j] && matrix[i][j] != '-') {
                    matches = true;
                    // remove the matching balloons and shift remaining balloons down
                    for (int k = i; k <= i + 2; k++) {
                        matrix[k][j] = '-';
                    }
                    for (int k = i - 1; k >= 0; k--) {
                        matrix[k + 3][j] = matrix[k][j];
                        matrix[k][j] = '-';
                    }
                }
            }
        }
        return matches;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // get the size of the matrix
        System.out.print("Enter the matrix size(m*n): ");
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        char[][] matrix = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = '-';
            }
        }

        // loop to get input until a row is filled or user chooses to stop
        char continueFlag = 'Y';
        while (continueFlag == 'Y') {
            // get column number and color of balloon from user
            System.out.print("Enter the column number: ");
            int col = scanner.nextInt() - 1; // adjust column index to start from 0
            System.out.print("Enter the color of the balloon: ");
            char color = scanner.next().charAt(0);

            // drop balloon in the column
            boolean columnFilled = true;
            for (int i = m - 1; i >= 0; i--) {
                if (matrix[i][col] == '-') {
                    matrix[i][col] = color;
                    columnFilled = false;
                    break;
                }
            }

            // print the current state of matrix
            System.out.println("Contents of the matrix:");
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }

            // check for matches and remove balloons if necessary
            if (checkForMatches(matrix)) {
                System.out.println("Three continuous balloons of the same color were found in one or more columns.");
            }

            // check if a row is filled
            boolean rowFilled = false;
            for (int i = 0; i < m; i++) {
                rowFilled = true;
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '-') {
                        rowFilled = false;
                        break;
                    }
                }
                if (rowFilled) {
                    System.out.println("Row " + (i + 1) + " is filled completely. Program is terminated.");
                    return;
                }
            }

// check if any column has three continuous balloons of same colors
            for (int i = 0; i < n; i++) {
                int count = 1;
                char prev = matrix[0][i];
                for (int j = 1; j < m; j++) {
                    if (matrix[j][i] == prev && matrix[j][i] != '-') {
                        count++;
                    } else {
                        count = 1;
                        prev = matrix[j][i];
                    }
                    if (count == 3) {
                        System.out.println("Three continuous balloons of color " + prev + " found in column " + (i + 1) + ". They will burst now.");
                        for (int k = j; k > j - 3; k--) {
                            matrix[k][i] = '-';
                        }
                        // print the updated matrix
                        System.out.println("Contents of the matrix:");
                        for (int a = 0; a < m; a++) {
                            for (int b = 0; b < n; b++) {
                                System.out.print(matrix[a][b] + " ");
                            }
                            System.out.println();
                        }
                        // reset the count to 1 and update the previous color
                        count = 1;
                        prev = matrix[j][i];
                    }
                }
            }

// ask user if they want to continue
            if (columnFilled) {
                System.out.println("Column " + (col + 1) + " is filled completely. Program is terminated.");
                continueFlag = 'N';
            } else {
                System.out.print("Do you wish to continue(Y/N): ");
                continueFlag = scanner.next().charAt(0);
            }
        }
    }
}



