package set0;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class SpiralPrint {

    static int SIZE = 10;



    // function to print the given matrix
    static void printMat(int mat[][], int n)
    {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print( mat[i][j] + " ");
            System.out.println();
        }
    }

    // Driver program to test above
    public static void main(String args[])
    {
        int mat[][] = { { 5, 4, 7 },
                { 1, 3, 8 },
                { 2, 9, 6 } };
        int n = 3;

        System.out.println("Original Matrix:");
        printMat(mat, n);
        sortRow(mat, n);

    }

    private static void sortRow(int[][] matrix, int n) {
        int top  = 0;
        int down = n-1;
        int left = 0;
        int right= n-1;

        char c;

        while(true)
        {
            //print first row
            for(int i=left;i<=right;++i)
                System.out.print(matrix[top][i]);
            top++;

            if(top>down || left>right)
                break;
            //print last column
            for(int i=top;i<=down;++i)
                System.out.print(matrix[i][right]);
            right--;

            if(top>down || left>right)
                break;
            //print last row
            for(int i=right;i>=left;--i)
                System.out.print(matrix[down][i]);
            down--;

            if(top>down || left>right)
                break;
            //print first column
            for(int i=down;i>=top;--i)
                System.out.print(matrix[i][left]);
            left++;

            if(top>down || left>right)
                break;
        }
    }

}
