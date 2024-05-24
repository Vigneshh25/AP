package Problems.set2;

import java.util.Stack;


public class ExpressionValidator {

    public static boolean isValid(String expression) {
        // Create a stack to keep track of parentheses
        Stack<Character> stack = new Stack<>();
        int i;

        // Iterate through the expression and check for unbalanced parentheses
        for (i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                // Push the opening parentheses onto the stack
                stack.push(c);
            } else if (c == ')') {
                // If the stack is empty, there is an unbalanced closing parentheses
                if (stack.isEmpty()) {
                    return false;
                }
                // Pop the top element from the stack, which should be an opening parentheses
                stack.pop();
            }
            else if(c=='+')
                if(!(expression.charAt(i-1)>96&&expression.charAt(i-1)<123&&expression.charAt(i+1)>96&&expression.charAt(i+1)<123))
                {
                    break;
                }
        }

        // If the stack is empty, all parentheses are balanced
        return stack.isEmpty() ;
    }

    public static void main(String[] args) {
        String expression1 = "(c+b)(a*b)";
        boolean result1 = isValid(expression1);
        System.out.println(result1);  // Output: true

        String expression2 = "(ab)(ab+m) ";
        boolean result2 = isValid(expression2);
        System.out.println(result2);  // Output: false

        String expression3 = "((a+b)";
        boolean result3 = isValid(expression3);
        System.out.println(result3);  // Output: false
    }
}
