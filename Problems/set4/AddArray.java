package Problems.set4;

import java.util.Scanner;

public class AddArray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        System.out.print("Number of elements in set1: ");
        int size1 = in.nextInt();

        int[] arraya = new int[size1];
        System.out.println("Enter the elements: ");
        for (int i = 0; i < size1; i++)
            arraya[i] = in.nextInt();

        System.out.print("Number of elements in set2: ");
        int size2 = in.nextInt();

        int[] arrayb = new int[size2];
        System.out.println("Enter the elements: ");
        for (int i = 0; i < size2; i++)
            arrayb[i] = in.nextInt();

        int maxSize = Math.max(size1, size2);
        int[] result = new int[maxSize];

        int carry = 0;

        for (int i = 0; i < maxSize; i++) {
            int sum = carry;

            if (i < size1) {
                sum += arraya[size1 - i - 1];
            }

            if (i < size2) {
                sum += arrayb[size2 - i - 1];
            }

            result[maxSize - i - 1] = sum % 10;
            carry = sum / 10;
        }

        if (carry != 0) {
            System.out.print(carry + ",");
        }

        for (int i = 0; i < maxSize - 1; i++) {
            System.out.print(result[i] + ",");
        }

        System.out.print(result[maxSize - 1]);
    }
}
