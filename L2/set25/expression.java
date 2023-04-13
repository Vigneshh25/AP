package set25;

public class expression {
    
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
            if (operator == '*' || operator == '/') {
                // Apply the multiplication or division operator to the current and next operands
                if (operator == '*') {
                    result = operand1 * operand2;
                } else {
                    result = operand1 / operand2;
                }

                // Set the first operand to the result
                operand1 = result;
            } else {
                // Apply the addition or subtraction operator to the current and previous results
                if (operator == '+') {
                    result = result + operand1;
                } else {
                    result = result - operand1;
                }

                // Set the first operand to the next operand
                operand1 = operand2;
            }
        }

        // Apply the final operator to the current and previous results
        if (operator == '+') {
            result = result + operand1;
        } else {
            result = result - operand1;
        }

        return result;
    }

    // Driver program to test above function
        public static void main(String[] args)
        {
            String expr1 = "1+2*5+3";
             evaluate(expr1);
          

            String expr2 = "1+2*3";
            evaluate(expr2);
           

            String expr3 = "4-2+6*3";
           evaluate(expr3);
            

            String expr4 = "1++2";
           evaluate(expr4);
           
        }
    }
// This code is contributed by mits


