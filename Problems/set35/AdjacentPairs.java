package Problems.set35;

import java.util.Scanner;

public class AdjacentPairs {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the first string:");
            String s1 = scanner.nextLine();
            System.out.println("Enter the second string:");
            String s2 = scanner.nextLine();
            scanner.close();

            printAdjacentPairs(s1, s2);
        }

        public static void printAdjacentPairs(String s1, String s2) {
            // Zip the two strings together and iterate over the pairs
            for (int i = 0; i < s1.length(); i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);
                // If the characters are not equal, print the pair
                if (c1 != c2) {
                    System.out.println(c1 + "" + c2);
                }
            }
        }
    }
