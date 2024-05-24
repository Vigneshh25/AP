package Problems.set23;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Intersection {
        public int[] intersection(int[] nums1, int[] nums2) {
            HashSet<Integer> set = new HashSet<Integer>();
            List<Integer> a = new ArrayList<Integer>();
            for(int i:nums1)
                set.add(i);
            for(int i:nums2)
            {
                if(set.remove(i))
                    a.add(i);
            }
            int[] ans = new int[a.size()];
            int j=0;
            for(int i:a)
                ans[j++] = i;
            return ans;
        }

}
