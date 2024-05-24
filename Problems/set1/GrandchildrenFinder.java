package Problems.set1;

import java.util.HashMap;
import java.util.Map;

public class GrandchildrenFinder {
    public static void main(String[] args) {
        String[][] input = {
                {"luke", "shaw"},
                {"wayne", "rooney"},
                {"rooney", "ronaldo"},
                {"shaw", "rooney"}};
        String s = "ronaldo";
        String father ="";
        for(String[] i:input)
        {
                if(i[1].equals(s))
                {
                    father = i[0];
                }
        }
        int count=0;
        for (String[] i :input)
        {
            if(i[1].equals(father))
                count++;
        }
        System.out.println(count);

    }
}
