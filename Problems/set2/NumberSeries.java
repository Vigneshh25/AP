package Problems.set2;

import java.util.Arrays;

public class NumberSeries {

    public static void main(String[] args) {

        int n = 8;
        int[] arr = new int[18];
        arr[0] = 3;
        arr[1] = 4;
        int k=1;
        for(int i=0;i<n;i++)
        {
            arr[++k] = (arr[i]*10)+3;
            arr[++k] = (arr[i]*10)+4;
        }
        System.out.println(Arrays.toString(arr));
    }
}
