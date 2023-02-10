package set7;

import java.util.Map;
import java.util.TreeMap;

public class d {
    public static void main(String[] args) {

        String ans = "";
        String input = "Aabb";

        TreeMap<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            map.put(String.valueOf(c),map.getOrDefault(c,0)+1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String c = entry.getKey();
            int frequency = entry.getValue();
            for (int i = 0; i < frequency; i++) {
                ans +=c;
            }
        }
        System.out.println(ans);
    }
}
