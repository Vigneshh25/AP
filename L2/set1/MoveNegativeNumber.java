package set1;

import java.util.Arrays;

public class MoveNegativeNumber {
    public static void main(String[] args) {
        int[] arr = {-1,4,5,6,-4,6,-2,-8};

        for(int i=0;i<arr.length;i++)
        {
            int j=i;
            while(j>0 && arr[j] > 0 &&arr[j-1]<0)
            {
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
                j--;
            }
        }
        System.out.println(Arrays.toString(arr
        ));
    }
}
