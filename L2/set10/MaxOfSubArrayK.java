class Solution
{
    //Function to find maximum of each subarray of size k.
    static ArrayList <Integer> max_of_subarrays(int arr[], int n, int k)
    {
        // Your code here
        Deque<Integer> q = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            if(!q.isEmpty()&&q.getFirst()==i-k)
                q.poll();
            while(!q.isEmpty()&&arr[q.getLast()]<arr[i])
                q.removeLast();
            q.add(i);
            if(i>=k-1)
                list.add(arr[q.peek()]);
        }
        return list;
    }
}
