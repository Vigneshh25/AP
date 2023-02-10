package set27;

import java.util.Arrays;

public class SortByFrequency {
    public static int[] sortByFrequency(int[] array) {
        // Count the frequency of each element
        int[] frequency = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int count = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[i] == array[j]) {
                    count++;
                }
            }
            frequency[i] = count;
        }

        // Sort the array in descending order according to the frequency of each element
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (frequency[i] < frequency[j] || (frequency[i] == frequency[j] && array[i] < array[j])) {
                    // Swap the elements and their frequencies
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    temp = frequency[i];
                    frequency[i] = frequency[j];
                    frequency[j] = temp;
                }
            }
        }

        return array;
    }

    public static void main(String[] args) {
        int[] array = {2, 2, 3, 4, 5, 12, 2, 3, 3, 3, 12};
        int[] sortedArray = sortByFrequency(array);
        System.out.println(Arrays.toString(sortedArray)); // Output: [3, 3, 3, 3, 2, 2, 2, 12, 12, 4, 5]
    }
}

//import java.util.ArrayList;
//        import java.util.HashMap;
//        import java.util.List;
//        import java.util.Map;
//        import java.util.Map.Entry;
//
//public class SortByFrequency {
//    public static List<Integer> sortByFrequency(int[] array) {
//        Map<Integer, Integer> frequencyMap = new HashMap<>();
//        for (int element : array) {
//            frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
//        }
//
//        // Sort the map by value in descending order
//        List<Entry<Integer, Integer>> sortedEntries = new ArrayList<>(frequencyMap.entrySet());
//        sortedEntries.sort((e1, e2) -> e2.getValue() - e1.getValue());
//
//        // Add the elements to a list in the order they appear in the sorted map
//        List<Integer> result = new ArrayList<>();
//        for (Entry<Integer, Integer> entry : sortedEntries) {
//            for (int i = 0; i < entry.getValue(); i++) {
//                result.add(entry.getKey());
//            }
//        }
//        return result;
//    }
//
//    public static void main(String[] args) {
//        int[] array = {2, 2, 3, 4, 5, 12, 2, 3, 3, 3, 12};
//        List<Integer> sortedArray = sortByFrequency(array);
//        System.out.println(sortedArray); // Output: [3, 3, 3, 3, 2, 2, 2, 12, 12, 4, 5]
//    }
//}


