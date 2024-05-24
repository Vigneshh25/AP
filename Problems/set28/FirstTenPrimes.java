package Problems.set28;

public class FirstTenPrimes {
    public static void main(String[] args) {
        int count = 0;
        int num = 2;  // start with the first prime number
        
        while (count < 10) {  // print the first 10 prime numbers
            boolean isPrime = true;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.print(num + " ");
                count++;
            }
            num++;
        }
    }
}



