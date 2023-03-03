package set2;

import java.util.*;

public class RemoveUnBalancedParanthesis {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the expression: ");
        String exp = input.nextLine();
        input.close();
        System.out.println("Original expression: " + exp);
        Stack<Integer> stack = new Stack<>();
        char[] arr = exp.toCharArray();
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] == '(')
            {
                stack.push(i);
            }
            else if (arr[i] == ')')
            {
                if (!stack.isEmpty())
                {
                    stack.pop();
                }
                else
                {
                    arr[i] = '\0'; // mark unbalanced ')'
                }
            }
        }
        while (!stack.isEmpty()) {
            arr[stack.pop()] = '\0'; // mark unbalanced '('
        }
        String result = new String(arr);
        result = result.replaceAll("\0", "");
        System.out.println("Balanced expression: " + result);

    }
}
