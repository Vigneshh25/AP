package Problems.set3;

// Java Program to sort even-placed elements in increasing and
// odd-placed in decreasing order with constant space complexity
import java.util.Arrays;

public class sortElement {

    public static void main(String[] args) {
        int[] arr = {13, 2, 4, 15, 12, 10, 5};
        int n =arr.length;

                for (int i = 0; i < n - 1; i++) {
                    for (int j = i + 1; j < n; j++) {
                        if (i % 2 == 0 && arr[i] > arr[j] || i % 2 != 0 && arr[i] < arr[j]) {
                            int temp = arr[i];
                            arr[i] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                System.out.print("Sorted array: ");
                for (int element : arr) {
                    System.out.print(element + " ");
                }
                System.out.println();
            }
        }
