package Problems.set8;



import java.util.Arrays;

class AlternatingSort {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                // Even Index or DESc order
                if (i % 2 == 0)
                {
                    if (arr[min_idx] < arr[j])
                    {
                        min_idx = j;
                    }
                }
                // odd Index or AESC order

                else {
                    if (arr[j] < arr[min_idx]) {
                        min_idx = j;
                    }
                }

            }
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }
}
