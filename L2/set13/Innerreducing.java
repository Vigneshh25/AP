package set13;

import java.util.Scanner;

public class Innerreducing {
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