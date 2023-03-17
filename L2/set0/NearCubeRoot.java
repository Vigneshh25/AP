package set0;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

public class NearCubeRoot {
    public static int nearestCubeRoot(int n) {
        int left = 1;
        int right = n;
        int mid = (left + right) / 2;

        while (left <= right) {
            mid = (left + right) / 2;
            int cube = mid * mid * mid;
            if (cube == n) {
                return mid;
            } else if (cube < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        // The closest cube root will be either mid or mid+1
        int cube1 = mid * mid * mid;
        int cube2 = (mid + 1) * (mid + 1) * (mid + 1);
        if (Math.abs(n - cube1) < Math.abs(n - cube2)) {
            return mid;
        } else {
            return mid + 1;
        }
    }

    public static void main(String[] args)
    {

        System.out.println(nearestCubeRoot(18));
    }

}
