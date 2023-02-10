package set27;

public class SubstringMatch {
    public static boolean isSubstring(String s1, String s2) {
        int i = 0;
        int j = 0;
        while (i < s1.length() && j < s2.length()) {
            // Check if the current characters match
            if (s1.charAt(i) == s2.charAt(j) || s2.charAt(j) == '*') {
                i++;
                j++;
            } else {
                // If the characters don't match, go back to the last * in s2
                while (j > 0 && s2.charAt(j - 1) != '*') {
                    j--;
                }
                i++;
            }
        }
        // Return true if the end of s2 is reached, false otherwise
        return j == s2.length();
    }

    public static void main(String[] args) {
        String s1 = "Spoon";
        String s2 = "Sp*n";
        System.out.println(isSubstring(s1, s2)); // Output: true

        s1 = "Zoho";
        s2 = "*o*o";
        System.out.println(isSubstring(s1, s2)); // Output: true

        s1 = "Man";
        s2 = "n*";
        System.out.println(isSubstring(s1, s2)); // Output: false

        s1 = "Subline";
        s2 = "line";
        System.out.println(isSubstring(s1, s2)); // Output: true
    }
}
