package set36;

public class ArrayModifier {
    public static void main(String[] args) {
        int[] array = {0, 2, 2, 2, 0, 6, 6, 0, 8};
        int[] modifiedArray = modifyArray(array);
        for (int i = 0; i < modifiedArray.length; i++) {
            System.out.print(modifiedArray[i] + " ");
        }
        System.out.println();
    }

    public static int[] modifyArray(int[] array) {
        // Initialize i to 0
        int i = 0;
        // Initialize an empty array to store the modified array
        int[] output = new int[array.length];
        // Modify the array
        int k = 0;
        while (i < array.length) {
            // If the element at the current index is not 0, add the sum of the element and the next occurrence of the element to the output array and set the next occurrence of the element to 0
            if (array[i] != 0) {
                output[k++] = array[i] + findNext(array, i + 1, array[i]);
                setNext(array, i + 1, array[i], 0);
            }
            // If the element at the current index is 0, add it to the output array and increment i by one
            else {
                output[k++] = array[i++];
            }
        }
        // Return the output array
        return output;
    }

    // Helper method to find the next occurrence of a given element in an array
    public static int findNext(int[] array, int start, int element) {
        for (int i = start; i < array.length; i++) {
            if (array[i] == element) {
                return array[i];
            }
        }
        return 0;
    }

    // Helper method to set the next occurrence of a given element in an array to a specified value
    public static void setNext(int[] array, int start, int element, int value) {
        for (int i = start; i < array.length; i++) {
            if (array[i] == element) {
                array[i] = value;
                return;
            }
        }
    }
}
