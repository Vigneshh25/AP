package Problems.set17;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static boolean hasSum(int[] arr, int target) {
        // Create a map to store the values that have been seen so far
        Map<Integer, Boolean> seenValues = new HashMap<>();

        // Iterate over the array, checking if the difference between the
        // target and the current value is present in the map
        for (int value : arr) {
            int difference = target - value;
            if (seenValues.containsKey(difference)) {
                return true;
            }
            seenValues.put(value, true);
        }

        // If no matching pair was found, return false
        return false;
    }
}

 class Mai {
    public static void main(String[] args) {
        int[] arr = new int[] {1, 3, 4, 8, 10};
        int target = 7;
        boolean hasSum = TwoSum.hasSum(arr, target);
        System.out.println(hasSum);  // Output: true
    }
}

