package set3;

class StringDecode {
    public static String expand(String s) {
        String result = "";
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            i++;
            int count = 0;
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                count = count * 10 + (s.charAt(i) - '0');
                i++;
            }
            if (count == 0) {
                count = 1;
            }
            while (count-- > 0) {
                result += c;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(expand("a1b10"));
        System.out.println(expand("b3c6d15"));
    }
}


