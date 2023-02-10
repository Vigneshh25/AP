package set32;

import java.util.HashMap;

public class Longsubseqnece {
    public static void main(String[] args) {
        String s = "abcccccbba";
        int count=-1;
        HashMap<Character,Integer> map=new HashMap<>();
        for(int i=0;i<s.length();i++)
        {
            if(map.containsKey(s.charAt(i)))
                count=Math.max(count,i-map.get(s.charAt(i))-1);
            else
                map.put(s.charAt(i),i);
        }

        System.out.println(count);


    }
}
