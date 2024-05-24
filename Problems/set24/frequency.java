package Problems.set24;

//User function Template for Java

class Solution {
    String printString(String S, char ch, int count) {
        // code here
  int i=0,cnt=0,n=S.length();
     boolean flag=false;
     String ans="";
     while(i<n){
         if(cnt==count){
             flag=true;
             break;
         };
         if(S.charAt(i)==ch){
             cnt++;
         }
         
         i++;
     }
     if(!flag) return "Empty string";
     return S.substring(i);
}
}
