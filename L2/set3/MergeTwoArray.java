package set3;

public class MergeTwoArray {
        public static int[] mergeArrays(int[] a, int[] b) {
            int n1 = a.length;
            int n2 = b.length;
            int[] c = new int[n1 + n2];
            int i = 0, j = 0, k = 0;
            while (i < n1 && j < n2) {
                if (a[i] < b[j]) {
                    c[k++] = a[i++];
                } else if (a[i] > b[j]) {
                    c[k++] = b[j++];
                } else {
                    c[k++] = a[i++];
                    j++;
                }
            }
            while (i < n1) {
                c[k++] = a[i++];
            }
            while (j < n2) {
                c[k++] = b[j++];
            }
            return c;
        }

        public static void main(String[] args) {
            int[] a = {2, 4, 5, 6, 7, 9, 10, 13};
            int[] b = {2, 3, 4, 5, 6, 7, 8, 9, 11, 15};
            int[] c = mergeArrays(a, b);
            for (int x : c) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

