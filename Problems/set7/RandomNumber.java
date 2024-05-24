package Problems.set7;

import java.util.Random;
import java.util.Scanner;

public class RandomNumber {

    /**
     * @Website: www.instanceofjava.com
     * @category: how to generate random numbers in java within range
     */
    public static void main(String[] args) {

        Random randomNumGenerator = new Random();

        for (int idx = 1; idx <= 10; ++idx){
            int randomInt = randomNumGenerator.nextInt(100);
            System.out.println("Random Number= "+randomInt);

            Scanner in = new Scanner(System.in);
            System.out.println("Enter minimum number");
            int minimum=in.nextInt();

            System.out.println("Enter maximum number");
            int maximum=in.nextInt();

            Random rn = new Random();
            int range = maximum - minimum + 1;
            int randomNum =  rn.nextInt(range) + minimum;

            System.out.println("Random Number= "+randomNum);



        }

    }

}