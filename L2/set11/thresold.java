package set11;

public class thresold {
    public static void main(String[] args) {
        int[] inputArray = {5, 8, 10, 13, 6, 2};
        int threshold = 3;
        int output = findCount(inputArray, threshold);
        System.out.println(output);  // Expected output: 17
    }

    public static int findCount(int[] arr, int t) {
        int count = 0;
        for (int x : arr) {
            int p = x / t;
            int r = x % t;
            count += p;
            if (r != 0) {
                count += 1;
            }
        }
        return count;
    }
}

