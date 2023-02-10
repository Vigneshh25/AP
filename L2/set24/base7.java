package set24;

public class base7 {
    // Java program to Find Number
// of digits in base b.

        // function to print number of digits
        static void findNumberOfDigits(long n, int base)
        {

            // Calculating log using base changing
            // property and then taking it
            // floor and then adding 1.
            int dig = (int)(Math.floor(
                    Math.log(n) / Math.log(base))
                    + 1);


            // printing output
            System.out.println("The Number of digits of Number "
                    + n + " in base " + base
                    + " is " + dig);
        }

        // Driver method
        public static void main(String[] args)
        {
            // taking inputs
            long n = 1446;
            int base = 7;

            // calling the method
            findNumberOfDigits(n, base);
        }
    }


