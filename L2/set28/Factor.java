package set28;

import java.util.Arrays;

public class Factor {

        public static void sortByMinFactor(Integer[] array) {
            Arrays.sort(array, (a, b) -> minFactor(b) - minFactor(a));
    };

        public static int minFactor(int n) {
                
                int count =0;
          for (int i=1;i<=Math.sqrt(n); i++)
            {  
                if (n%i==0)
                {
                    // If divisors are equal, print only one
                    if (n/i == i)
                        count++;     
                    else // Otherwise print both
                       count+=2;
                } 
            }
        }

        public static void main(String[] args) {
            Integer[] array = {12, 15, 21, 30, 7};
            sortByMinFactor(array);
            System.out.println(Arrays.toString(array)); // Output: [7, 15, 21, 30, 12]
        }
    }



