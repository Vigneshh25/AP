package set7;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class NumberToLetterCombinations {
    // Lookup table for mapping digits to letters
    private static final String[] DIGIT_TO_LETTERS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        String number = scanner.nextLine();  // read the number as a string
        Queue<String> combinations = generateCombinations(number);  // generate the combinations
        System.out.println("All possible combinations for the number " + number + " are:");
        while (!combinations.isEmpty()) {
            System.out.println(combinations.poll());  // print each combination
        }
    }

    // Generate all possible combinations of letters for the given number
    public static Queue<String> generateCombinations(String number) {
        Queue<String> combinations = new LinkedList<>();
        generateCombinations(number, new StringBuilder(), combinations);  // start the recursive process
        return combinations;
    }

    // Recursive function to generate all possible combinations of letters
    private static void generateCombinations(String number, StringBuilder combination, Queue<String> combinations) {
        if (number.length() == 0) {  // base case: if the number is empty, add the combination to the queue
            combinations.offer(combination.toString());
            return;
        }

        // Get the current digit and the corresponding letters
        char digit = number.charAt(0);
        String letters = DIGIT_TO_LETTERS[digit - '0'];

        // Recursively generate combinations for each letter
        for (int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);
            combination.append(letter);  // append the letter to the combination
            generateCombinations(number.substring(1), combination, combinations);
            combination.deleteCharAt(combination.length() - 1);  // remove the last character from the combination
        }
    }
}
