package Problems.set38;

import java.util.Set;
import java.util.TreeSet;

public class REmoveDUPLICATE {

    public static String removeDuplicates(String s) {
        // Create a set to store the unique characters in the string
        Set<Character> set = new TreeSet<>();
        // Iterate over the string and add each character to the set
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        // Concatenate the elements of the set into a single string
        StringBuilder sb = new StringBuilder();
        for (char c : set) {
            sb.append(c);
        }
        // Return the resulting string
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates("aaabbc"));
    }
}
