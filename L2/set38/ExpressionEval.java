import java.util.Stack;

 class ArithmeticExpressionEvaluation {
    public static int evaluateArithmeticExpression(String expression) {
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch)) {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num.append(expression.charAt(i));
                    i++;
                }
                i--; // Move back one step to handle the next character properly
                operands.push(Integer.parseInt(num.toString()));
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    int b = operands.pop();
                    int a = operands.pop();
                    char operator = operators.pop();
                    int result = performOperation(a, b, operator);
                    operands.push(result);
                }
                operators.pop(); // Pop the '(' from the stack
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                    int b = operands.pop();
                    int a = operands.pop();
                    char operator = operators.pop();
                    int result = performOperation(a, b, operator);
                    operands.push(result);
                }
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            int b = operands.pop();
            int a = operands.pop();
            char operator = operators.pop();
            int result = performOperation(a, b, operator);
            operands.push(result);
        }

        return operands.peek();
    }

    private static int precedence(char operator) {
        if (operator == '*' || operator == '/') {
            return 2;
        } else if (operator == '+' || operator == '-') {
            return 1;
        }
        return 0;
    }

    private static int performOperation(int a, int b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        String expression = "((2+3)*(5/2))";
        int result = evaluateArithmeticExpression(expression);
        System.out.println("Result: " + result); // Output: 10
    }
}
