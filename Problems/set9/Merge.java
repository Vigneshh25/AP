package Problems.set9;

// Java program to merge two sorted arrays
//using maps
import java.io.*;
import java.util.*;

class Merge{
    // Function to merge arrays
    public static void main(String[] args)
    {
     int[] arr1 = {1, 2, 3, 6, 9};
        int[] arr2 = {2, 4, 5, 10};
        List<Integer> ans = new ArrayList<>();
        int l=0;
        int r=0;
        int ind = 0;
        while (l< arr1.length&&r< arr2.length)
        {
            if(arr1[l]<arr2[r])
            {
                ans.add((arr1[l]));
                l++
                ;
            }
            else if(arr1[l]>arr2[r]) {
                ans.add(arr2[r]);
                r++;
            }
            else 
            l++;

        }
        while (l< arr1.length)
            ans.add(arr1[l++]);
        while (r<arr2.length)
            ans.add(arr2[r++]);
        System.out.println(ans);
    }
}

// This code is contributed by rag2127

