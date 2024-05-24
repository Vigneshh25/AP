package Problems.set37;

import java.util.Arrays;

public class ArrayModi {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 2, 2, 2, 0, 6, 6, 0, 0, 8};
        modifyAndRearrangeArr(arr, arr.length);

    }
    static void modifyAndRearrangeArr (int arr[], int n)
    {// Complete the function
        for(int i=0;i<n-1;i++){
            if(arr[i]!=0 && arr[i]==arr[i+1]){
                arr[i]=2*arr[i];
                arr[i+1]=0;
            }
        }
        int l=0;
        for(int r=0;r<n;r++){
            if(arr[r]!=0){
                int t=arr[l];
                arr[l]=arr[r];
                arr[r]=t;
                l++;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
