package set11;// Java implementation of above approach

class degree
{

    static int N = 4;

    public static void horizontalFlip(int[][] imgArray)
    {
        int temp;
        for (int i = 0; i < imgArray.length; i++) {
            for (int j = 0; j < imgArray[i].length/2; j++) {
                temp = imgArray[i][j];
                imgArray[i][j] = imgArray[i][imgArray[i].length - 1 - j];
                imgArray[i][imgArray[i].length - 1 - j] = temp;
            }
        }
    }
     public static void horizontalVeritically(int[][] a)
    {
    int temp;
        for (int i = 0; i < a.length/2; i++) 
        {
            for (int j = 0; j < a[i].length; j++) 
            {
                temp = a[i][j];
                a[i][j] = a[a.length-1-i][j];
                a[a.length-1-i][j] = temp;
            }
        }
     }

    private void flipHorizontally() {
        for (int y = 0; y < totalY; y++) {
            for (int x = 0; x < totalX/2; x++) {
                String tmp = fields[totalX-x-1][y].getText();
                fields[totalX-x-1][y].setText(fields[x][y].getText());
                fields[x][y].setText(tmp);
            }
        }
    }


    private void flipVertically() {
        for (int x = 0; x < totalX; x++) {
            for (int y = 0; y < totalY/2; y++) {
                String tmp = fields[x][totalY - y - 1].getText();
                fields[x][totalY - y - 1].setText(fields[x][y].getText());
                fields[x][y].setText(tmp);
            }
        }
    }

    // Function to rotate the matrix 90 degree clockwise
    static void rotate90Clockwise(int a[][])
    {


//        for(int i=0;i<N;i++)
//        {
//            for (int j = i+1; j < N; j++)
//            {
//                int temp = a[i][j];
//                a[i][j] = a[j][i];
//                a[j][i] = temp;
//            }
//        }
//        for(int i=0;i<N;i++)
//        {
//            int l = 0;
//            int h = N-1;
//            while (l<h)
//            {
//                int temp = a[i][h];
//                a[i][h] = a[i][l];
//                a[i][l] = temp;
//                l++;
//                h--;
//            }
//        }


        // Traverse each cycle
        for (int i = 0; i < N / 2; i++)
        {
            for (int j = i; j < N - i - 1; j++)
            {

                // Swap elements of each cycle
                // in clockwise direction
                int temp = a[i][j];
                a[i][j] = a[N - 1 - j][i];
                a[N - 1 - j][i] = a[N - 1 - i][N - 1 - j];
                a[N - 1 - i][N - 1 - j] = a[j][N - 1 - i];
                a[j][N - 1 - i] = temp;
            }
        }
    }

    // Function for print matrix
    static void printMatrix(int arr[][])
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print( arr[i][j] + " ");
            System.out.println();
        }
    }

// Driver code

    public static void main (String[] args)
    {
        int arr[][] = { { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        rotate90Clockwise(arr);
        printMatrix(arr);
    }
}

// This code has been contributed by inder_verma.
