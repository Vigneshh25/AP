package Problems.set9;

import java.util.Arrays;

class equilibriumPoint {
     public static int equilibriumPoint(long arr[], int n) {

         // Your code here
         long sum = 0;
         long leftsum = 0;
         sum = Arrays.stream(arr).sum();
         for (int i = 0; i < n; i++) {
             sum -= arr[i];
             if (sum == leftsum)
                 return i + 1;
             leftsum += arr[i];
         }
         return -1;
     }
 }
