package set28;

public class NumberContains {
    public static void main(String[] args) {
        int a = 123;
        int b = 456789123;

        String aStr = Integer.toString(a);
        String bStr = Integer.toString(b);

        for(int i=0;i<=bStr.length()-aStr.length();i++)
        {
            String s = bStr.substring(i,i+aStr.length());
            if(s.contains(aStr))
                System.out.println("true");

        }



    }
}
