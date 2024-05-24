package Problems.set42;

public class IntToRoman {
        public String intToRoman(int num) {
            StringBuilder res = new StringBuilder();
            String sym[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            int val[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            for(int i=0;num!=0;i++)
            {
                while(val[i]<=num)
                {
                    res.append(sym[i]);
                    num = num - val[i];
                }
            }
            return res.toString();
        }
        public int romanToInt(String s) {
            int prev = 0;
            int answer = 0;
            int number = 0;
            for(int i=s.length()-1;i>=0;i--)
            {
                switch(s.charAt(i))
                {
                    case 'M' -> number = 1000;
                    case 'D' -> number = 500;
                    case 'C' -> number = 100;
                    case 'L' -> number = 50;
                    case 'X' -> number = 10;
                    case 'V' -> number = 5;
                    case 'I' -> number = 1;
                }
                if(number<prev)
                    answer -=number;
                else
                    answer +=number;
                prev = number;
            }
            return answer;
    }
}
