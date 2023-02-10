package set24;

public class Rectangle {
    // A simple program to find if
// given 4 values can represent
// 4 sides of rectangle

        // Function to check if the given
        // integers value make a rectangle
        static boolean isRectangle(int a, int b, int c, int d)
        {
            // Square is also a rectangle
            if (a == b && a == c && a == d && c == d && b == c
                    && b == d)
                return true;

            else if (a == b && c == d)
                return true;
            else if (a == d && c == b)
                return true;
            else if (a == c && d == b)
                return true;
            else
                return false;
        }

        // Driver code
        public static void main(String[] args)
        {

            int a = 1, b = 2, c = 3, d = 4;
            if (isRectangle(a, b, c, d))
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }

// This code is contributed by prerna saini.


