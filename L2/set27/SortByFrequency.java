package set27;

import java.util.Arrays;

public class SortByFrequency {
   public static void main(String[] args) {
        Map<Integer, Integer> m = new HashMap<>();

        Integer[] arr = { 4, 4, 5, 6, 4, 2, 2, 8, 5 };
        int n = arr.length;
        for(int i:arr)
            m.compute(i,(k,v)->v==null?1:v+1);
        Arrays.sort(arr,(a,b)->m.get(b)-m.get(a));
        System.out.println(Arrays.toString(arr));
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


