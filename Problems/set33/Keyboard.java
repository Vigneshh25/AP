package Problems.set33;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {
    public static void main(String[] args) {
        String digits = "23";
        String[] mapping = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        List<String> words = new ArrayList<>();
        findWords(digits, mapping, "", words);

        for (String word : words) {
            System.out.println(word);
        }

    }


        public static void findWords(String digits, String[] mapping, String prefix, List<String> words) {
            if (digits.length() == 0) {
                words.add(prefix);
                return;
            }

            int digit = digits.charAt(0) - '0';
            String letters = mapping[digit - 1];

            for (int i = 0; i < letters.length(); i++) {
                char letter = letters.charAt(i);
                findWords(digits.substring(1), mapping, prefix + letter, words);
            }


    }
}
