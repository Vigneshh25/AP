package Problems.set1_0;

public class MoveNegativesToEnd {
    public static void main(String[] args) {
        int[] arr = {1, -2, 3, -3, 1, 71, -64};
        moveNegativesToEnd(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void moveNegativesToEnd(int[] arr) {
        int n = arr.length;
        int negIndex = 0;

        // Traverse the array and move negative numbers to the end
        for (int i = 0; i < n; i++) {
            if (arr[i] >= 0) {
                // If the element is non-negative, swap it with the first negative number found
                int temp = arr[i];
                arr[i] = arr[negIndex];
                arr[negIndex] = temp;
                negIndex++;
            }
        }
    }
}
