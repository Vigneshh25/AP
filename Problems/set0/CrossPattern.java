package Problems.set0;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CrossPattern {

    public static void main(String[] args) {

       String s = "water";
       for(int i=1;i<=s.length();i++)
       {
           for(int j=1;j<=s.length();j++)
           {

               if(j==i||(i+j)==s.length()+1|| j==s.length()/2+1||(i==s.length()/2+1))
               {

                   if(i==s.length()/2+1)
                       System.out.print(s.charAt(j - 1) + " ");
                   else
                       System.out.print(s.charAt(i - 1) + " ");
               }
               else
                   System.out.print("  ");
           }
           System.out.println();
       }
    }
}
