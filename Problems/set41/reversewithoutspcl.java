package Problems.set41;

import java.util.Stack;

public class reversewithoutspcl {
    public String reverse(String str)
    {
        Stack<Character> stack = new Stack<>();
        int n = str.length();
        for(int i = 0; i < n; i++){
            char c = str.charAt(i);
            if((c<='Z' && c>='A') || (c<='z' && c>='a')) stack.push(c);
        }
        StringBuilder sb = new StringBuilder(n);
        for(int i = 0; i < n; i++){
            char c = str.charAt(i);
            if((c<='Z' && c>='A') || (c<='z' && c>='a')) sb.append(stack.pop());
            else sb.append(str.charAt(i));

        }
        return sb.toString();
    }
}
