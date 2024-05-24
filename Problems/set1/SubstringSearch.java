package Problems.set1;

//#include <stdio.h>
//        int main(void) {
//        char *string="welcometozohocorporation";
//        char array[5][5];
//        int i,j,k;
//        for(i=0;i<5;i++)
//        {
//        for(j=0;j<5;j++)
//        {
//        array[i][j]=string[k];
//        k++;
//        }
//        }
//        for(i=0;i<5;i++)
//        {
//        for(j=0;j<5;j++)
//        {
//        if(array[i][j]=='t' && array[i][j+1]=='o' && array[i][j+2]=='o')
//        {
//        printf("Start index:<%d,%d>\n",i,j);
//        printf("End index:<%d,%d>",i,j+2);
//        }
//        }
//        }
//        for(i=0;i<5;i++)
//        {
//        for(j=0;j<5;j++)
//        {
//        if(array[i][j]=='t' && array[i+1][j]=='o' && array[i+2][j]=='o')
//        {
//        printf("Start index:<%d,%d>\n",i,j);
//        printf("End index:<%d,%d>",i+2,j);
//        }
//        }
//        }
//        return 0;
//        }
// Java program to search
// a word in a 2D grid
import java.io.*;
import java.util.*;

class SubstringSearch {

    // Rows and columns in the given grid
    static int R, C;

    // For searching in all 8 direction
    static int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };

    // This function searches in all
    // 8-direction from point
    // (row, col) in grid[][]
    static boolean search2D(char[][] grid, int row,
                            int col, String word)
    {
        // If first character of word
        // doesn't match with
        // given starting point in grid.
        if (grid[row][col] != word.charAt(0))
            return false;

        int len = word.length();

        // Search word in all 8 directions
        // starting from (row, col)
        for (int dir = 0; dir < 8; dir++) {
            // Initialize starting point
            // for current direction
            int k, rd = row + x[dir], cd = col + y[dir];

            // First character is already checked,
            // match remaining characters
            for (k = 1; k < len; k++) {
                // If out of bound break
                if (rd >= R || rd < 0 || cd >= C || cd < 0)
                    break;

                // If not matched, break
                if (grid[rd][cd] != word.charAt(k))
                    break;

                // Moving in particular direction
                rd += x[dir];
                cd += y[dir];
            }

            // If all character matched,
            // then value of must
            // be equal to length of word
            if (k == len)
                return true;
        }
        return false;
    }

    // Searches given word in a given
    // matrix in all 8 directions
    static void patternSearch(
            char[][] grid,
            String word)
    {
        // Consider every point as starting
        // point and search given word
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (grid[row][col]==word.charAt(0) &&
                        search2D(grid, row, col, word))
                    System.out.println(
                            "pattern found at " + row + ", " + col);
            }
        }
    }

    // Driver code
    public static void main(String args[])
    {
        R = 3;
        C = 13;
        char[][] grid = { { 'G', 'E', 'E', 'K', 'S', 'F', 'O', 'R', 'G', 'E', 'E', 'K', 'S' },
                { 'G', 'E', 'E', 'K', 'S', 'Q', 'U', 'I', 'Z', 'G', 'E', 'E', 'K' },
                { 'I', 'D', 'E', 'Q', 'A', 'P', 'R', 'A', 'C', 'T', 'I', 'C', 'E' } };
        patternSearch(grid, "GEEKS");
        System.out.println();
        patternSearch(grid, "EEE");
    }
}

// This code is contributed by rachana soma
