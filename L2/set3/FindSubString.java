package set3;

class FindSubString {
    public static int findSubstring(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        for (int i = 0; i < n1 - n2 + 1; i++) {
            if (s1.substring(i, i + n2).equals(s2)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findSubstring("test123string", "123"));
        System.out.println(findSubstring("testing12", "1234"));
    }

}





