package org.example.QueryEngine;

import java.util.Arrays;

/**
 * Created by Vignesh.V on 10/04/23.
 */
public class MergeArray {
    public static void main(String[] args) {
        int [] a = {1, 2, 3, 6, 9};
        int[] b = {2,4,5,10};
        int[] ans = new int[a.length+ b.length];
        int i=0;
        int j =0;
        int k = 0;
        while (i< a.length&&j<b.length)
        {
            if(a[i]<b[j])
            {
                if (k == 0 || a[i] != ans[k-1]) {
                    ans[k++] = a[i++];
                } else {
                    i++;
                }
            }
            else
            {
                if (k == 0 || b[j] != ans[k-1]) {
                    ans[k++] = b[j++];
                } else {
                    j++;
                }
            }
        }
        while(i<a.length)
        {
            if (k == 0 || a[i] != ans[k-1]) {
                ans[k++] = a[i++];
            } else {
                i++;
            }
        }
        while (j<b.length)
        {
            if (k == 0 || b[j] != ans[k-1]) {
                ans[k++] = b[j++];
            } else {
                j++;
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOf(ans, k)));
    }
}
