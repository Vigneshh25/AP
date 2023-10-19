package L2.set0;

import java.util.Stack;

class StackProblems {
    // Function to convert an infix expression to a postfix expression.
    static int pre(char c) {
        if (c == '+' || c == '-') return 1;
        else if (c == '*' || c == '/') return 2;
        else if (c == '^') return 3;
        else return -1;
    }

    public static String infixToPostfix(String exp) {

        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char c : exp.toCharArray()) {
            if (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9') {
                sb.append(c);
            } else if (c == '(') stack.push(c);
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') sb.append(stack.pop());
                stack.pop();
            } else {
                while (!stack.isEmpty() && pre(c) <= pre(stack.peek())) {
                    sb.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    static String preToInfix(String pre_exp) {
        // code here

        Stack<String> stack = new Stack<String>();
        // StringBuilder sb = new StringBuilder();
        for (int i = pre_exp.length() - 1; i >= 0; i--) {
            char c = pre_exp.charAt(i);
            if (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9') {
                stack.push(c + "");
            } else {
                String a = stack.pop();
                String b = stack.pop();
                stack.push("(" + (a + c + b) + ")");
            }
        }
        return stack.pop();

    }

    static String preToPost(String pre_exp) {
        // code here
        Stack<String> stack = new Stack<>();
        for (int i = pre_exp.length() - 1; i >= 0; i--) {
            char c = pre_exp.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                stack.push(c + "");
            } else {
                String a = stack.pop();
                String b = stack.pop();
                stack.push(a + b + c);
            }
        }
        return stack.pop();
    }

    static String postToPre(String pre_exp) {
        // code here
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < pre_exp.length(); i++) {
            char c = pre_exp.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                stack.push(c + "");
            } else {
                String a = stack.pop();
                String b = stack.pop();
                stack.push(c + b + a);
            }
        }
        return stack.pop();
    }

    static String postToInfix(String str) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((c >= 97 && c <= 122) || (c >= 65 && c <= 90)) stack.push(c + "");
            else {
                String a = stack.pop();
                stack.push("(" + stack.pop() + c + a + ")");
            }
        }
        return stack.pop();
    }

    static boolean isalpha(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    static boolean isdigit(char c) {
        return c >= '0' && c <= '9';
    }

    static boolean isOperator(char c) {
        return (!isalpha(c) && !isdigit(c));
    }

    static int getPriority(char C) {
        if (C == '-' || C == '+') return 1;
        else if (C == '*' || C == '/') return 2;
        else if (C == '^') return 3;

        return 0;
    }

    // Reverse the letters of the word
    static String reverse(char[] str, int start, int end) {

        // Temporary variable to store character
        char temp;
        while (start < end) {

            // Swapping the first and last character
            temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }
        return String.valueOf(str);
    }

    static String infixToPostfix(char[] infix1) {
        System.out.println(infix1);
        String infix = '(' + String.valueOf(infix1) + ')';

        int l = infix.length();
        Stack<Character> char_stack = new Stack<>();
        String output = "";

        for (int i = 0; i < l; i++) {

            // If the scanned character is an
            // operand, add it to output.
            if (isalpha(infix.charAt(i)) || isdigit(infix.charAt(i))) output += infix.charAt(i);

                // If the scanned character is an
                // ‘(‘, push it to the stack.
            else if (infix.charAt(i) == '(') char_stack.add('(');

                // If the scanned character is an
                // ‘)’, pop and output from the stack
                // until an ‘(‘ is encountered.
            else if (infix.charAt(i) == ')') {
                while (char_stack.peek() != '(') {
                    output += char_stack.peek();
                    char_stack.pop();
                }

                // Remove '(' from the stack
                char_stack.pop();
            }

            // Operator found
            else {
                if (isOperator(char_stack.peek())) {
                    while ((getPriority(infix.charAt(i)) < getPriority(char_stack.peek())) || (getPriority(infix.charAt(i)) <= getPriority(char_stack.peek()) && infix.charAt(i) == '^')) {
                        output += char_stack.peek();
                        char_stack.pop();
                    }

                    // Push current Operator on stack
                    char_stack.add(infix.charAt(i));
                }
            }
        }
        while (!char_stack.empty()) {
            output += char_stack.pop();
        }
        return output;
    }

    static String infixToPrefix(char[] infix) {
        /*
         * Reverse String Replace ( with ) and vice versa Get Postfix Reverse Postfix *
         */
        int l = infix.length;

        // Reverse infix
        String infix1 = reverse(infix, 0, l - 1);
        infix = infix1.toCharArray();

        // Replace ( with ) and vice versa
        for (int i = 0; i < l; i++) {

            if (infix[i] == '(') {
                infix[i] = ')';
                i++;
            } else if (infix[i] == ')') {
                infix[i] = '(';
                i++;
            }
        }

        String prefix = infixToPostfix(infix);

        // Reverse postfix
        prefix = reverse(prefix.toCharArray(), 0, l - 1);

        return prefix;
    }


}
