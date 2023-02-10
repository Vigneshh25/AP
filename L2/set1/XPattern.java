package set1;


public class XPattern {

    public static void main(String[] args) {
        String str = "HELLO WORLD";
        int length = str.length();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i == j || i + j == length - 1) {
                    System.out.print(str.charAt(j));
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}



