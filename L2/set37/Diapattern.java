package set37;

public class Diapattern {
    public static void main(String[] args) {
        int n =3;
        int space = n-1;
        int start =1;
        for(int i=1;i<2*n;i++)
        {
            for(int j=1;j<=space;j++)
            {
                System.out.print(" ");
            }
            for (int k=1;k<=start;k++)
                System.out.print("* ");
            System.out.println();
            if(i<n)
            {
                space--;
                start++;
            }
            else
            {
                space++;
                start--;
            }
        }
    }
}
