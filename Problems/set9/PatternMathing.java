package Problems.set9;

public class PatternMathing {
    public static boolean match(String str, String pattern) {
        int s = 0, p = 0;
        if(str.length()==0) return true;
        while (s < str.length() && p < pattern.length()) {
            char c = pattern.charAt(p);
            if (c == '*') {
                // Skip consecutive * characters in the pattern
                while (p < pattern.length() && pattern.charAt(p) == '*') {
                    p++;
                }
                // If the pattern ends with *, it matches the rest of the string
                if (p == pattern.length()) {
                    return true;
                }
                // Try to match the rest of the pattern with the rest of the string
                while (s < str.length()) {
                    if (match(str.substring(s), pattern.substring(p))) {
                        return true;
                    }
                    s++;
                }
                return false;
            } else if (c == '?' || c == str.charAt(s)) {
                // Match one character
                s++;
                p++;
            } else {
                // Mismatch
                return false;
            }
        }
        // Check if we have reached the end of both the string and the pattern
        return s == str.length() && p == pattern.length();
    }

    // Example of how to use the match function
    public static void main(String[] args) {
        String str = "";
        String pattern = "***";

        if (match(str, pattern)) {
            System.out.println("The string matches the pattern.");
        } else {
            System.out.println("The string does not match the pattern.");
        }
    }

}
