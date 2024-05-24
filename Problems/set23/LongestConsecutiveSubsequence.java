package Problems.set23;

import java.util.HashSet;

class LongestConsecutiveSubsequence {

    public static int findLongestConseqSubseq(int arr[], int n) {
        HashSet<Integer> set = new HashSet<Integer>();
        int longestSubseqLen = 0;

        // Insert all elements of the array into a HashSet
        for (int i = 0; i < n; i++) {
            set.add(arr[i]);
        }

        // Iterate over the array
        for (int i = 0; i < n; i++) {
            int current = arr[i];

            // If current element is the starting element of a sequence
            if (!set.contains(current - 1)) {
                int count = 1;
                while (set.contains(current + count)) {
                    count++;
                }
                longestSubseqLen = Math.max(longestSubseqLen, count);
            }
        }

        return longestSubseqLen;
    }

    public static void main(String args[]) {
        int arr[] = {2, 6, 1, 9, 4, 5, 3};
        int n = arr.length;
        System.out.println(findLongestConseqSubseq(arr, n));
    }
}
