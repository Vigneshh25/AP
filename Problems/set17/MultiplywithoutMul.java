package Problems.set17;

import java.util.Scanner;

public class MultiplywithoutMul {


        public static void main(String args[])
        {
            Scanner sc=new Scanner(System.in);
            int x, y, sum=0;
            System.out.print("Enter the first number: ");
            x=sc.nextInt();
            System.out.print("Enter the second number: ");
            y=sc.nextInt();
            //executes until the condition becomes false
            for(int i=1;i<=x;i++)
            {
                //calculates the sum
                sum=sum+y;
            }
            //prints the result
            System.out.println("The multiplication of "+x+" and "+y+" is: "+sum);
        }
    }

