package set0;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxSecondFreq {

    public static void main(String[] args) {

        int arr[] = {15, 1,12, 13, 1,110,10,10,1,10,10,12,12,12};
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i:arr)
            map.put(i,map.getOrDefault(i,0)+1);
        int maxvalue = 0;
        int maxcount = 0;
        int secondcount = 0;
        int secondvalue = maxvalue;
        System.out.println(map);
        for(Map.Entry<Integer, Integer> m: map.entrySet())
        {
            if(m.getValue()>maxcount)
            {
                secondcount = maxcount;
                secondvalue = maxvalue;
                maxcount = m.getValue();
                maxvalue = m.getKey();
            }
            else if(m.getValue()>secondcount && maxcount!=m.getValue())
            {
                secondcount = m.getValue();
                secondvalue = m.getKey();
            }
        }
        System.out.println(secondvalue);

    }
}
