package Problems.set0;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class RearrageArray {

    // Driver program to test above
    public static void main(String args[])
    {
        int[] arr = {1,2,3,4,5,6,7,8};
        for(int i=1;i<arr.length-1;i++)
        {
            int temp  = arr[i+1];
            arr[i+1] = arr[i];
            arr[i] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }
}
