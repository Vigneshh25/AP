package set27;

public class SubstringMatch {
    public static boolean isSubstring(String A, String B) {
         int j=0;
        for(int i=0;i<B.length()&&j<A.length();i++)
        {
            if(A.charAt(j)==B.charAt(i)||B.charAt(i)=='*')
            {
                j++;
            }
            
        }
        return j==A.length();
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
