package set4;

public class pattern {
    public static String lookAndSay(String s) {
        StringBuilder result = new StringBuilder();
        int count = 1;
        char current = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == current) {
                count++;
            } else {
                result.append(count).append(current);
                current = s.charAt(i);
                count = 1;
            }
        }
        result.append(count).append(current);
        return result.toString();
    }


    public static void main(String[] args) {

        String s = "1";
        for (int i = 1; i <= 10; i++) {
            System.out.println(s);
            s = lookAndSay(s);
        }

    }

    }


