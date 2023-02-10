package set13;

public class reverse {
    public static void main(String[] args) {
        String str = "A man, in the boat says : I see 1-2-3 in the sky";
        char[] chars = str.toCharArray();
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (!Character.isAlphabetic(chars[left])) {
                left++;
            } else if (!Character.isAlphabetic(chars[right])) {
                right--;
            } else {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }

        str = new String(chars);
        System.out.println(str);

    }
}
