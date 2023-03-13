package set26;

import javax.swing.text.StyleContext;
import java.util.HashMap;
import java.util.Map;

public class secondfreq {

    public static void main(String[] args) {
        // The series of numbers
        int[] numbers = { 3, 3, 4, 5, 4, 3, 1, 1, 3, 4};

        Map<Integer, Integer> countMap = new HashMap<>();

// Count the occurrences of each number in the series
        for (int num : numbers) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int maxCount = -1;
        int secondMaxCount = -1;
        int secondMaxNum = -1;
        int max = -1;

        System.out.println(countMap);

// Find the second highest count
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int count = entry.getValue();
            if (count > maxCount) {
                secondMaxCount = maxCount;
                maxCount = count;
                secondMaxNum = max;
                max = entry.getKey();
            } else if (count > secondMaxCount && count != maxCount) {
                secondMaxCount = count;
                secondMaxNum = entry.getKey();
            }
        }

// Print the second most frequent number
        System.out.println("The second most frequent number is: " + secondMaxNum);
    }}




