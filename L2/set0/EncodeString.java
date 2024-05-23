public class EncodeString {
    public static String compressString(String str) {
        StringBuilder compressedString = new StringBuilder();
        int count = 1;
        for (int i = 0; i < str.length(); i++) {
            if (i == str.length() - 1 || str.charAt(i) != str.charAt(i + 1)) {
                compressedString.append(str.charAt(i));
                compressedString.append(count);
                count = 1;
            } else {
                count++;
            }
        }
        return compressedString.toString();
    }
    
    public static void main(String[] args) {
        String inputString = "aaaaabbccaad";
        String compressedString = compressString(inputString);
        System.out.println(compressedString);
    }
}
