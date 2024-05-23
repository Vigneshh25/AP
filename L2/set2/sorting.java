class AlternateSort {
    static void alternateSort(int arr[], int n) {
        Arrays.sort(arr);
        int i=0;
        int j= n-1;
        int me = arr[n-1]+1;
        int ind =0;

        while(ind<n)
        {
            if(ind%2==0)
            {
                arr[ind++] += (arr[j]%me)*me;
                j--;
            }
            else
            {
                arr[ind++] +=(arr[i]%me)*me;
                i++;
            }

        }
        System.out.println(Arrays.toString(arr));
        for(int k:arr)
        {
            System.out.print(k/me +" ");
        }
    }

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int[] arr = {1,2,3,4,5};
       alternateSort(arr, arr.length);
    }
}
