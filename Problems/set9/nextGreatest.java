package Problems.set9;

class nextGreatest {
     void nextGreatest(int arr[], int n) {
         // code here
         int max = arr[n - 1];
         for (int i = n - 2; i >= 0; i--) {
             int temp = arr[i];
             arr[i] = max;
             if (temp >= max) {
                 max = temp;
             }

         }
         arr[n - 1] = -1;
     }
 }