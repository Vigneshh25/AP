package Problems.set36;

public class Patter {
    public static void main(String[] args) {
        int n = 4;
        for(int i=1;i<=n;i++)
        {
            int a = i*(i+1)/2;
            for(int k=1;k<=n-i;k++)
                System.out.print(" ");
            for(int j=1;j<=i;j++)
            {
                System.out.print(a+" ");
                a--;
            }
            System.out.println();
        }
        for(int i=n;i>=0;i--)
        {
            int a = i*(i+1)/2;
            for(int k=1;k<=n-i;k++)
                System.out.print(" ");
            for(int j=1;j<=i;j++)
            {
                System.out.print(a+" ");
                a--;
            }
            System.out.println();
        }

    }
}
