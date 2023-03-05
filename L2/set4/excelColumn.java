
class Solution {
    public String excelColumn(int N){
        
        //  Your code here
        
        StringBuilder ans=new StringBuilder();
        while(N!=0){
            N--;
            int mod=N%26;
            ans.append((char)('A'+mod));
            N/=26;
        }
        return ans.reverse().toString();
        
        
    }
}
