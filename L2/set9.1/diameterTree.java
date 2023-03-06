class Solution {
    // Function to return the diameter of a Binary Tree.
    
    int diameter(Node root) {
        // Your code here
        int[] arr = new int[1];
        int dia = height(root,arr);
        return arr[0]+1;
    }
    int height(Node root,int[] arr)
    {
        if(root==null)
            return 0;
        int l = height(root.left,arr);
        int r = height(root.right,arr);
        arr[0] = Math.max(arr[0],l+r);
        return 1 + Math.max(l,r);
        
    }
}
