package set13;

public class PrintElements {
    public static void printElements(int[] arr) {
        for (int i = 0; i < arr.length-1; i=i+2) {
            if (arr[i] > arr[i+1]) {
                System.out.println(arr[i]);
            }
            else
                System.out.println(arr[i+1]);
        }
    }

    public static void main(String[] args) {
        int[]a = new int[]{ 2, -3, -4, 5, 9, 7, 8};
        printElements(a);
    }
}
