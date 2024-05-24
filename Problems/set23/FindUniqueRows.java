package Problems.set23;

// Given a binary matrix of M X N
// of integers, you need to return
// only unique rows of binary array
import java.io.*;
import java.util.*;

class FindUniqueRows{
    static int ROW = 4;
    static int COL = 5;
    // Function that prints all
// unique rows in a given matrix.
    
    
    public static ArrayList<ArrayList<Integer>> uniqueRow(int a[][], int r, int c)
    {
        //add code here.
       ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    for(int[] i:a)
    {
        ArrayList<Integer> list = new ArrayList<>();
       for(int j : i) {
        list.add(j);
            }
        if(!ans.contains(list))
            ans.add(list);
    }
    return ans;
    }
    
    
    static void findUniqueRows(int M[][])
    {
        // Traverse through the matrix
        for(int i = 0; i <ROW; i++)
        {
            int flag = 0;
            // Check if there is similar column
            // is already printed, i.e if i and
            // jth column match.
            for(int j = 0; j < i; j++)
            {
                flag = 1;
                for(int k = 0; k < COL; k++)
                    if (M[i][k] != M[j][k])
                        flag = 0;
                if (flag == 1)
                    break;
            }
            // If no row is similar
            if (flag == 0)
            {
                // Print the row
                for(int j = 0; j < COL; j++)
                    System.out.print(M[i][j] + " ");
                System.out.println();
            }
        }
    }
    public static void main(String[] args)
    {
        int M[][] = { { 0, 1, 0, 0, 1 },
                { 1, 0, 1, 1, 0 },
                { 0, 1, 0, 0, 1 },
                { 1, 0, 1, 0, 0 } };

        findUniqueRows(M);
    }
}

