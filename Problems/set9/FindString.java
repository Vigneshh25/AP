package Problems.set9;

import java.util.ArrayList;
import java.util.List;

public class FindString {
    public static void main(String[] args) {
        String passage = "jana Gana Mana jana jana";
        String word = "jana";

        int count = 0;
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < passage.length() - word.length() + 1; i++) {
            boolean match = true;
            for (int j = 0; j < word.length(); j++) {
                if (passage.charAt(i + j) != word.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                count++;
                indices.add(i);
            }
        }

        System.out.println("Number of occurrences: " + count);
        System.out.println("Indices: " + indices);

    }
}
