package e;

import java.util.*;
import java.util.regex.*;

public class Lexer {

    private List<String> tokens;

    public Lexer() {
        this.tokens = new ArrayList<String>();
    }

    @Override
    public String toString() {
        return "Lexer{" +
                "tokens=" + tokens +
                '}';
    }

    public List<String> tokenize(String input) {

        String[] lines = input.split("\\n");
        System.out.println(Arrays.toString(lines));

        for (String line : lines) {
            String[] words = line.split("\\s+");
            for (String word : words) {
                if (word.matches("[a-zA-Z]+")) {
                    tokens.add("IDENTIFIER:" + word);
                } else if (word.matches("[0-9]+")) {
                    tokens.add("CONSTANT:" + word);
                } else if (word.matches("[+\\-*/%]")) {
                    tokens.add("OPERATOR:" + word);
                } else if (word.matches("[(){},;]")) {
                    tokens.add("PUNCTUATION:" + word);
                } else {
                    tokens.add("ERROR:" + word);
                }
            }
        }

        return tokens;
    }
}
 class Main {
    public static void main(String[] args) {
        String input = "int a = 10;\nfloat b = 3.14;\nif (a > b) {\n  System.out.println(\"a is greater\");\n} else {\n  System.out.println(\"b is greater\");\n}";
        Lexer lexer = new Lexer();
        List<String> tokens = lexer.tokenize(input);
        for (String token : tokens) {
            System.out.println(token);
        }
    }
}
