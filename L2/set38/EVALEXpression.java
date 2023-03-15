package set28;

import java.util.Arrays;

public class expressionn {

    public static double evaluate(String expr) {
        int i = 0;
        double operand1 = 0, operand2 = 0, result = 0;
        char operator = ' ';

        // Extract the first operand
        while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
            operand1 = operand1 * 10 + (expr.charAt(i) - '0');
            i++;
        }

        // Extract the operators and operands and apply them in order
        while (i < expr.length()) {
            operator = expr.charAt(i);
            i++;

            // Extract the second operand
            operand2 = 0;
            while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                operand2 = operand2 * 10 + (expr.charAt(i) - '0');
                i++;
            }

            // Apply the operator to the operands
            switch (operator) {
                case '+':
                    result = operand1 + operand2;
                    break;
                case '-':
                    result = operand1 - operand2;
                    break;
                case '*':
                    result = operand1 * operand2;
                    break;
                case '/':
                    result = operand1 / operand2;
                    break;
                    case '^':
                    result = Math.pow(operand1,operand2) ;
                    break;
            }

            // Set the first operand to the result
            operand1 = result;
        }

        return result;
    }

    // Driver program to test above function
    public static void main(String[] args)
    {
        String[] arr = {"1+2*5+3","1+2*3","4-2+6*3","5","2^2^2"};
        Arrays.sort(arr,(a,b)-> (int) (evaluate(a)-evaluate(b)));
        System.out.println(Arrays.toString(arr));
        String expr1 = "1+2*5+3";
        evaluate(expr1);

        System.out.println( evaluate(expr1));


        String expr2 = "1+2*3";
        evaluate(expr2);
        System.out.println( evaluate(expr2));


        String expr3 = "4-2+6*3";
        evaluate(expr3);
        System.out.println( evaluate(expr3));


        String expr4 = "2^2^2";
        evaluate(expr4);
        System.out.println( evaluate(expr4));


    }
}
// This code is contributed by mits


