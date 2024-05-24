package Problems.set39;

public class diagonalSUm {
    static void sum(int mat[][], int r, int c)
    {
        int i, j;
        int upper_sum = 0;
        int lower_sum = 0;

        /*Calculate sum of upper triangle*/
        for (i = 0; i < r; i++)
            for (j = 0; j < c; j++) {
                if (i <= j) {
                    upper_sum += mat[i][j];
                }
            }

        System.out.println("Upper sum is " + upper_sum);

        /*Calculate sum of lower*/
        for (i = 0; i < r; i++)
            for (j = 0; j < c; j++) {
                if (j <= i) {
                    lower_sum += mat[i][j];
                }
            }

        System.out.print("Lower sum is " + lower_sum);
    }

    // Driver code
    public static void main(String[] args)
    {
        int r = 3;
        int c = 3;

        /*Giving the matrix*/
        int mat[][]
                = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

        /*Calling the function*/
        sum(mat, r, c);
    }
}