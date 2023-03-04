package set1;

public class WordReverser {
    public static String reverseWords(String str) {
        System.out.println(str);
        // Base case: if the input string is empty or has only one word, return it as is
        if (str == null || str.isEmpty() || !str.contains(" ")) {
            return str;

        }

        // Recursive case: reverse the substring after the first word and append the first word to the end
        return reverseWords(str.substring(str.indexOf(" ") + 1)) + " " + str.substring(0, str.indexOf(" "));
    }

    public static void main(String[] args) {
        // Example usage:
        String inputString = "one two three";
        String reversedString = reverseWords(inputString);
        System.out.println("Input string: " + inputString);
        System.out.println("Reversed string: " + reversedString);
    }
  
  String reverseWords(String str)
    {
        // code here 
       String[] words = str.split("\\.");

        // Reverse the order of the words
        int i = 0;
        int j = words.length - 1;
        while (i < j) {
            String temp = words[i];
            words[i] = words[j];
            words[j] = temp;
            i++;
            j--;
        }

        // Join the reversed words back into a string with dots as separators
        return String.join(".", words);
    }
}
