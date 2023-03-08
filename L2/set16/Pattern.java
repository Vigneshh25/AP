
import java.util.Scanner;

public class Pattern {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            String s = sc.next();
            for (int i=0;i<s.length();i++)
            {
                for (int j=0;j<s.length()-i;j++)
                    System.out.print(" ");
                int mid = s.length()/2;
                for (int j=0;j<=i;j++)
                {
                    System.out.print(s.charAt(mid%s.length()));
                    mid++;
                }
                System.out.println();
            }
        }
}
