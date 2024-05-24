package Problems.set13;

import java.util.Scanner;

public class Innerreducing {
    
     void printSquare(int n) {
        // code here
        for(int i=1;i<2*n;i++)
        {
            for(int j=1;j<2*n;j++)
            {
                int nn = Math.max(Math.abs(n-i)+1,Math.abs(n-j)+1);
                System.out.print(nn+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);
        int n=sc.nextInt();
        int limit=0;
        int s=n;
        int a[][]= new int[n][n];
        int l=0;int r=n-1;
        while(limit<n){
            for(int i=l;i<=r;i++){
                for(int j=l;j<=r;j++)
                    if(i==l || i==r || j==l || j==r)
                        a[i][j]=n;
            }
            l++;r--;n--;limit++;
        }
        for(int i=0;i<s;i++){
            for(int j=0;j<s;j++)
                System.out.print(a[i][j]);
            System.out.println();
        }
    }
}
