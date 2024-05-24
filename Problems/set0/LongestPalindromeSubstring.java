package Problems.set0;

import java.util.Map;

public class LongestPalindromeSubstring {
    public static void main(String[] args) {
        String s =  "cbbbccdddddaras";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        System.out.println(s.substring(start,end+1));

    }

    private static int expandAroundCenter(String s,int l,int r)
    {
        while(l>=0 && r<s.length()&&s.charAt(l)==s.charAt(r))
        {
            l--;
            r++;
        }
        return r-l-1;

    }

    public String longestPalindrome(String s) {
        if(s==null) return null;
        String longest = s.substring(0,1);
        for(int i=0; i<s.length(); i++){
            //odd case
            String pal = expand(s,i,i);
            if(pal.length()>longest.length()){
                longest = pal;
            }
            pal = expand(s,i,i+1);
            if(pal.length()>longest.length()){
                longest = pal;
            }
        }
        return longest;
    }

    static String expand(String s, int left, int right){
        if(left>right) return null;
        while(left>=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return s.substring(left+1, right);
    }
}
