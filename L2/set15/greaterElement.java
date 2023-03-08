class Complete{
    
   
    // Function for finding maximum and value pair
  
    
    public static int solve(ArrayList<Integer> a, int n, int x) {
        
        int start = 0;
        int end = n-1;
        int ans = -10000000;
        
        while(start <= end) {
            int mid = (start + end)/2;
            
            if(a.get(mid) == x) {
                ans = mid;
                start = mid+1;
                // break;
            }
            
            else if(a.get(mid) > x) {
                end = mid - 1;
            }
            
            else {
                start = mid + 1;
            }
        }
        
        // System.out.print(ans + " ");
        return ans == n-1 ? -10000000 : a.get(ans+1);
    }

    public static int[] greaterElement(int arr[], int n)
    {
        // Complete the function
        int[] ans = new int[n];
        ArrayList<Integer> a = new ArrayList<>();
        
        for(int i = 0 ; i < n; ++i) {
            a.add(arr[i]);
        }
        
        Collections.sort(a);

        for(int i = 0 ; i < n; ++i) {
            
            int sol = solve(a, n, arr[i]);
            
            ans[i] = sol;
        }
        // System.out.println();
        return ans;
    }
    
public static int[] findMinimumGreater(int[] arr) {
    int[] result = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
        int minGreater = Integer.MAX_VALUE;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] > arr[i] && arr[j] < minGreater) {
                minGreater = arr[j];
            }
        }
        result[i] = minGreater == Integer.MAX_VALUE ? -1 : minGreater;
    }
    return result;
}

}
