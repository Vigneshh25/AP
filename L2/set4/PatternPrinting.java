import java.util.*;

public class PatternPrinting {
    public static void main(String[] args) {
        char[][] c = new char[5][5];
        int m =5,n=5;
        int k=0,l=0;
        char x = 'X';
        while(k<m&&l<n)
        {
            for (int i=l;i<n;i++)
                c[k][i] = x;
            k++;
            for(int i=k;i<m;i++)
                c[i][n-1] = x;
            n--;
            if(k<m)
            {
                for (int i=n-1;i>=l;i--)
                    c[m-1][i] =x;
            }
            m--;
            if(l<n)
            {
                for (int i=m-1;i>=k;i--)
                    c[i][l] = x;
                l++;
            }

            x = x=='X'?'O':'X';
        }
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
                System.out.print(c[i][j] + " ");
            System.out.println();
        }

    }
}
