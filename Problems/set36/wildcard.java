package Problems.set36;

class Solution {
    int wildCard(String p, String s) {
        int slen = s.length(); // length of input.txt string s
        int plen = p.length(); // length of pattern string p
        int i = 0; // current index in s
        int j = 0; // current index in p
        int iStar = -1; // index of last '*' in p
        int jStar = -1; // index of last '*' in p

        while (i < slen) { // loop through s
            if (j < plen && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) { // if pattern matches character in s or pattern has '?'
                i++;
                j++;
            } else if (j < plen && p.charAt(j) == '*') { // if pattern has '*'
                iStar = i; // update index of last '*'
                jStar = j;
                j++;
            } else if (iStar >= 0) { // if mismatch happens and we have seen '*' before
                i = ++iStar; // backtrack to the position after the last '*'
                j = jStar + 1;
            } else { // if mismatch happens and we have not seen '*' before
                return 0;
            }
        }

        while (j < plen && p.charAt(j) == '*') { // consume remaining '*' characters in pattern
            j++;
        }

        return j == plen?1:0;
    }
}
