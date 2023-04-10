

//User function Template for Java

class RemoveDuplicate {
    int remove_duplicate(int A[],int N){
        // code here
        int i=0;
        for(int j=1;j<A.length;j++)
        {
            if(A[i]!=A[j])
            {
                i++;
                A[i] = A[j];
            }
        }
        return i+1;
    }
}
