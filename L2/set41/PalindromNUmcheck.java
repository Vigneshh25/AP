package set41;

public class PalindromNUmcheck {
    public static void main(String[] args) {
        int n = 477;
        int temp = n;
        int reverse = 0;
        while(n>0)
        {
             reverse = reverse* 10 +n%10;
             n /=10;
        }
        System.out.println("   "+temp+"   "+reverse);
    }
}
