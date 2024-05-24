package Ballon;

import java.util.Scanner;

public class BalloonGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the matrix size(m*n): ");
        int m = input.nextInt();
        int n = input.nextInt();
        char[][] matrix = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = '-';
            }
        }
        boolean continueGame = true;
        while (continueGame) {
            System.out.print("Enter the column number: ");
            int column = input.nextInt() - 1;
            System.out.print("Enter the color of the balloon: ");
            char color = input.next().charAt(0);

            int row = findFirstFreeRow(matrix);
            if (row == -1) {
                System.out.println("Column is already full!");
            } else if( matrix[row][column]=='-') {
                matrix[row][column] = color;
                printMatrix(matrix);
                checkForThreeContinuousBalloons(matrix, column);
            }
            else
            {
                for(int k=0;k<m;k++)
                {
                    if(matrix[row][k]=='-')
                    {
                        matrix[row][k] = color;
                        printMatrix(matrix);
                        checkForThreeContinuousBalloons(matrix, column);
                        break;
                    }
                }
            }
            System.out.print("Do you wish to continue(Y/N): ");
            String answer = input.next();
            continueGame = answer.equalsIgnoreCase("Y");
        }
        System.out.println("Program Terminated.");
    }

    public static void printMatrix(char[][] matrix) {
        System.out.println("Contents of the matrix:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int findFirstFreeRow(char[][] matrix) {
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j=matrix[0].length-1;j>=0;j--)
            {
                if(matrix[i][j]=='-')
                    return i;
            }
        }
        return -1;
    }

    public static void checkForThreeContinuousBalloons(char[][] matrix, int column) {
        // Check for three continuous balloons in the same column
        for (int i = 0; i <= matrix.length - 3; i++) {
            if (matrix[i][column] == matrix[i + 1][column] && matrix[i][column] == matrix[i + 2][column]&& matrix[i][column] != '-') {
                matrix[i][column] = '-';
                matrix[i + 1][column] = '-';
                matrix[i + 2][column] = '-';
                System.out.println("Three continuous balloons of the same color bursted in column " + (column + 1));
            }
        }

        // Check for three continuous balloons in the same row
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <= matrix[i].length - 3; j++) {
                if (matrix[i][j] == matrix[i][j + 1] && matrix[i][j] == matrix[i][j + 2]&& matrix[i][j] != '-') {
                    matrix[i][j] = '-';
                    matrix[i][j + 1] = '-';
                    matrix[i][j + 2] = '-';
                    System.out.println("Three continuous balloons of the same color bursted in row ");
                }
            }
        }
    }

}
