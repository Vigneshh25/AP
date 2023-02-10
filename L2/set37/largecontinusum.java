package set37;

public class largecontinusum {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,-2,5};
        System.out.println(maxSumContiguousSubarray(arr));
    }
    public static int maxSumContiguousSubarray(int[] array) {
        // Initialize the maximum sum to the minimum possible value
        int maxSum = Integer.MIN_VALUE;
        // Initialize the running sum to 0
        int runningSum = 0;
        // Iterate over the array
        for (int element : array) {
            // Update the running sum
            runningSum = Math.max(runningSum + element, element);
            // Update the maximum sum
            maxSum = Math.max(maxSum, runningSum);
        }
        // Return the maximum sum
        return maxSum;
    }

}
