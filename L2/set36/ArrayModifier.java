public class NumberContains {
    public static void main(String[] args) {
        int[] arr = {0, 2, 2, 2, 0, 6, 6, 0, 8};
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {

             if (arr[i] == arr[i + 1]) {
                arr[i] += arr[i + 1];
                arr[i+1]=0;

            }
        }

        // Move all zeros to the end of the array
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                arr[j++] = arr[i];
            }
        }
        while (j < arr.length) {
            arr[j++] = 0;
        }

        // Print the modified array
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }


}

