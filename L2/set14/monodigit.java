package set14;

public class monodigit {

    public static int monoDigit(int num) {
        int sum = 0;
        int n = num;
        while( n>0 || sum >9)
        {
            if(n==0)
            {
                n = sum;
               sum = 0;
            }
            sum += n%10;
            n /=10;
        }
        return sum;
    }

    public static void main(String[] args) {
        // Example usage
        System.out.println(monoDigit(1234));  // Output: 1+2+3+4 = 10, 1+0 = 1, Mono-digit number = 1
        System.out.println(monoDigit(98765)); // Output: 9+8+7+6+5 = 35, 3+5 = 8, Mono-digit number = 8
    }
}
