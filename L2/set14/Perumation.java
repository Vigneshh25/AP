import java.util.*;

public class PermutationsAndCombinations {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = input.nextLine();
        
        // Printing all permutations of the string
        System.out.println("All permutations:");
        printPermutations(str, "");

        // Printing all combinations of the string
        System.out.println("All combinations:");
        printCombinations(str, "");
    }
    
    // Function to print all permutations of a string
    public static void printPermutations(String str, String permutation) {
        if (str.length() == 0) {
            System.out.print(permutation + " ");
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String newStr = str.substring(0, i) + str.substring(i+1);
            printPermutations(newStr, permutation + ch);
        }
    }
    
    // Function to print all combinations of a string
    public static void printCombinations(String str, String combination) {
        if (str.length() == 0) {
            System.out.print(combination + " ");
            return;
        }
        char ch = str.charAt(0);
        String newStr = str.substring(1);
        printCombinations(newStr, combination + ch);
        printCombinations(newStr, combination);
    }
}
