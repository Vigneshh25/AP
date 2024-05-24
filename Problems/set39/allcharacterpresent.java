package Problems.set39;

import java.util.Scanner;

public class allcharacterpresent {

            public static void main(String[] args) {
                // Read the sentence from the user
                Scanner scanner = new Scanner(System.in);
                String sentence = scanner.nextLine();
                // Convert the sentence to lowercase
                sentence = sentence.toLowerCase();
                // Initialize a boolean array to store the presence of each letter
                boolean[] letters = new boolean[26];
                // Iterate over the sentence and set the corresponding element in the array to true as you encounter each letter
                for (char c : sentence.toCharArray()) {
                    letters[c - 'a'] = true;
                }
                // Check if all elements in the array are true
                boolean allLettersPresent = true;
                for (boolean letter : letters) {
                    if (!letter) {
                        allLettersPresent = false;
                        break;
                    }
                }
                // Print the result
                System.out.println(allLettersPresent ? "True" : "False");
            }
        }

