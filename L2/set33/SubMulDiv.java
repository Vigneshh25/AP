package set33;

import java.util.Scanner;

public class SubMulDiv {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter first integer: ");
                int a = scanner.nextInt();
                System.out.print("Enter second integer: ");
                int b = scanner.nextInt();

                // Subtraction
                int result = a + (-b);
                System.out.println(a + " - " + b + " = " + result);

                // Multiplication
                result = 0;
                for (int i = 0; i < b; i++) {
                    result += a;
                }
                System.out.println(a + " * " + b + " = " + result);

                // Division
                result = 0;
                int remainder = a;
                while (remainder >= b) {
                    remainder -= b;
                    result++;
                }
                System.out.println(a + " / " + b + " = " + result + " remainder " + remainder);
            }
        }

