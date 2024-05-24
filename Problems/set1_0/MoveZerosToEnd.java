package Problems.set1_0;

public class MoveZerosToEnd {

    public static void moveZeros(int[] nums) {
        int nonZeroIndex = 0; // Points to the index where the next non-zero element should be placed.

        // Iterate through the array.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // Swap the non-zero element with the element at nonZeroIndex.
                int temp = nums[i];
                nums[i] = nums[nonZeroIndex];
                nums[nonZeroIndex] = temp;
                nonZeroIndex++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 2, 0, 3, 4, 0, 5};
        moveZeros(nums);

        // Print the modified array.
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
