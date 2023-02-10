package set23;

class couneven {
        public static void main(String[] args) {
                int n = 12345;
                int even =0,odd=0;
                while(n>0)
                {
                        int i = n%10;
                        if(i%2==0)
                                even++;
                        else
                                odd++;
                        n/=10;
                }
                System.out.println(even  +"   "+odd);
        }
}