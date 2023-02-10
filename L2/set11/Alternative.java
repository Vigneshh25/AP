package set11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Alternative {
    public static void main(String[] args) {
        int[] arr = new int[]{ 2, 8, 7, 4, 3, 9,5};
        int n = arr.length;

        for(int i=0;i<n;i++)
        {
            for(int j=i+1;j<n;j++)
            {
                if(i%2==0)
                {
                    if(arr[i]<arr[j])
                    {
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
                else
                {
                    if(arr[i]>arr[j])
                    {
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
