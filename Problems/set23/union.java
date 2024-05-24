package Problems.set23;

import java.util.Arrays;

public class union {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {2, 3, 4};

        int[] union = union(arr1, arr2);
        for (int element : union) {
            System.out.print(element + " ");
        }
        // Output: 1 2 3 4
    }

    public static int[] union(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                result[k++] = arr1[i++];
            } else if (arr1[i] > arr2[j]) {
                result[k++] = arr2[j++];
            } else {
                result[k++] = arr1[i++];
                j++;
            }
        }
        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }
        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }
        return Arrays.copyOfRange(result, 0, k);
    }
}
