package Problems.set37;

import java.util.HashMap;
import java.util.Map;

public class TwoSm {
    public static void twoSum(int[] array, int x) {
        // Create a map to store the elements of the array and their indices
        Map<Integer, Integer> map = new HashMap<>();
        // Insert the elements of the array into the map
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], i);
        }
        // Iterate over the array and check for pairs with the desired sum
        for (int i = 0; i < array.length; i++) {
            int element = array[i];
            if (map.containsKey(x - element)) {
                System.out.println(i+"   "+map.get(x - element));
            }
        }
        // Return null if no pair is foun
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 4, 3, 5, 6};
        int x = 5;
        twoSum(array,5);
    }
}
