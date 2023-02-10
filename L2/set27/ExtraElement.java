package set27;

import java.util.Arrays;

public class ExtraElement {

    public static void main(String[] args) {
        int[] array1 = {10, 20, 30, 12, 5};
        int[] array2 = {10, 5, 30, 20};
        Arrays.sort(array1);
        Arrays.sort(array2);
        int i=0;
        while(i<array1.length-1 &&array1[i]==array2[i])
            i++;
        System.out.println(array1[i] + " is the extra element in array 1 at index " + array1[1]);
    }
}
