package set3;


public class ReverseWords {
    public static void main(String[] args) {
        String s = "I love india";
        String[] words = s.split("\\s+");
        String reversed = "";
        for (int i = words.length - 1; i >= 0; i--) {
            reversed += words[i] + " ";
        }
        System.out.println(reversed.trim());
        reverse(s,s.length());

    }

    private static void reverse(String s, int length) {
        int i=0;
        if(s==null)
            return;
        else
        {
            System.out.print(s.charAt(length-1));
            reverse(s.substring(0,length-1),length-1);
        }
    }
}
