package Problems.set0;

import java.util.*;

class CycleFound {


    public static void main(String[] args) {
        List<int[]> values1 = new ArrayList<>();
        values1.add(new int[]{1, 2});
        values1.add(new int[]{2, 4});
        values1.add(new int[]{5, 6});
        values1.add(new int[]{4, 7});
        values1.add(new int[]{6, 1});
        Collections.sort(values1, Comparator.comparingInt(a -> a[0]));
        for(int[] values:values1)
        {
            System.out.println(Arrays.toString(values));
        }
        boolean flag = false;
        int first = values1.get(0)[0];
        int second = values1.get(0)[1];
        int last = values1.get(values1.size()-1)[1];
        if(first!=last)
            System.out.println("No cycle - 1");
        else {
            for (int i = 1; i < values1.size(); i++) {
                    if(values1.get(i)[0]!=second)
                        System.out.println("No cycle");
                    else
                        second = values1.get(i)[1];

            }
        }
        System.out.println("Cycle");

    }
}
