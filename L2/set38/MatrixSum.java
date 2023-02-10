package set38;

public class MatrixSum {
    public static int arraySum(int[][] arrays) {
        int sum = 0;
        for (int[] array : arrays) {
            for (int num : array) {
                sum += num;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] arrays = {
                {3, 5, 4, 2},
                {2, 4, 5},
                {4, 5, 6, 7, 8},
                {4, 9, 2, 1},
                {1, 2}
        };
        System.out.println(arraySum(arrays)); // prints 31

    }
}
