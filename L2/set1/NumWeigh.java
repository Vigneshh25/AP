package set1;


import java.util.*;

public class NumWeigh {
    public static void main(String[] args) {
        int[] data = new int[]{10, 36, 54,89,12};
        int[] weights = new int[data.length];
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(data);
        for (int i:data)
        {
            int sum =0;
            int sqt = (int)Math.sqrt(i);
            if(sqt*sqt==i)
                sum+=5;
            if(i%4==0 && i%6==0)
                sum+=4;
            if(i%2==0)
                sum+=3;
            List<Integer> pair = new ArrayList<>(Arrays.asList(i,sum));
            list.add(pair);
        }
        Collections.sort(list, Comparator.comparingInt(a -> a.get(1)));
        System.out.println(list);
    }
}
