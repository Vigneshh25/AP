package set8;

// Java Program to print rectangular
// inner reducing pattern
public class Pattern {

    // function to compute pattern
    public static void innerPattern(int n)
    {


        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n-i;j++)
            {
                System.out.print(" ");
            }
            int k = 2*i-1;
            for (int j=1;j<=2*i-1;j++)
            {
                System.out.print(k);
                if(j<i)
                {
                    k = k-2;
                }
                 if(i<=j)
                    k = k+2;
            }
            System.out.println();
        }

        for(int i=1;i<2*n;i++)
        {
            for(int j=1;j<2*n;j++)
            {
                int k = Math.max(Math.abs(n-i)+1,Math.abs(n-j)+1);
                System.out.print(k+" ");

            }
            System.out.println();
        }
        // Pattern Size
        int size = 2 * n - 1;
        int front = 0;
        int back = size - 1;
        int a[][] = new int[size][size];
        while (n != 0) {
            for (int i = front; i <= back; i++)
            {
                for (int j = front; j <= back; j++)
                {
                    if (i == front || i == back || j == front || j == back)
                        a[i][j] = n;
                }
            }
            ++front;
            --back;
            --n;
        }
        print(a, size);
    }

    // function to Print pattern
    public static void print(int a[][], int size)
    {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
    }

    // RemoveNum Method
    public static void main(String[] args)
    {
        int n = 5; // Input
        innerPattern(n);
    }

}



