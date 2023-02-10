package set28;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemoveNum {
    public static void main(String[] args) {
        // Initialize the list of numbers
        List<Integer> numbers = new ArrayList<>();
        numbers.add(12345);
        numbers.add(67890);
        numbers.add(98765);
        numbers.add(54321);

        Scanner scanner = new Scanner(System.in);
        while (!numbers.isEmpty()) {
            // Prompt the user for a digit
            System.out.print("Enter a digit: ");
            int digit = scanner.nextInt();

            // Remove all occurrences of the digit from the list
            numbers.removeIf(number -> number.toString().contains(Integer.toString(digit)));

//            for (int i = 0; i < numbers.size(); i++) {
//                int number = numbers.get(i);
//                if (number.toString().contains(Integer.toString(digit))) {
//                    numbers.remove(i);
//                    i--;
//                }
//            }

            // Print the updated list
            System.out.println(numbers);
        }
    }
}
