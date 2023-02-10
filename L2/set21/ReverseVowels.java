package set21;

public class ReverseVowels {

    public static String reverseVowels(String s) {
        char[] result = s.toCharArray();
        int i = 0;
        int j = result.length - 1;
        while (i < j) {
            if (isVowel(result[i]) && isVowel(result[j])) {
                char temp = result[i];
                result[i] = result[j];
                result[j] = temp;
                i++;
                j--;
            } else if (isVowel(result[i])) {
                j--;
            } else if (isVowel(result[j])) {
                i++;
            } else {
                i++;
                j--;
            }
        }
        return new String(result);
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
