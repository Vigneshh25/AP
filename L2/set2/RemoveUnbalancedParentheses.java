//import java.util.Stack;
//
//public class RemoveUnbalancedParentheses {
//    public static String removeUnbalancedParentheses(String s) {
//        // Create a stack to store parentheses
//        Stack<Character> stack = new Stack<>();
//        // Create a string builder to store the result
//        StringBuilder sb = new StringBuilder();
//        // Iterate through the input string
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if(c=='(')
//            {
//                while (s.charAt(i++)=='(')
//            }
//        }
//        // After the loop, add any remaining open parentheses from the stack to the result string
//        while (!stack.isEmpty()) {
//            sb.append(stack.pop());
//        }
//        return sb.toString();
//    }
//
//    public static void main(String[] args) {
//        String input1 = "((abc)((de))";
//        String output1 = removeUnbalancedParentheses(input1);
//        System.out.println(output1); // ((abc)(de))
//
//        String input2 = "(a(b)))(cd)";
//        String output2 = removeUnbalancedParentheses(input2);
//        System.out.println(output2); // (a(b))(cd)
//
//        String input3 = "(a(b)))(c(d)";
//        String output3 = removeUnbalancedParentheses(input3);
//        System.out.println(output3); // (a(b))(cd)
//
//        String input4 = "(ab))(c(d))))";
//        String output4 = removeUnbalancedParentheses(input4);
//        System.out.println(output4); // (ab)(c(d))
//
//        String input5 = "(((ab)";
//        String output5 = removeUnbalancedParentheses(input5);
//        System.out.println(output5); // (ab)
//    }
//}
