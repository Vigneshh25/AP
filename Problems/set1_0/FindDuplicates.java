package Problems.set1_0;

import java.util.ArrayList;

/**
 * Created by Vignesh.V on 20/10/23.
 */

class FindDuplicates {
    public static ArrayList<Integer> duplicates(int arr[], int n) {
        // code here
        ArrayList<Integer> duplicates = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int index = arr[i] % arr.length;
            arr[index] += arr.length;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] / arr.length > 1) {
                duplicates.add(i);
            }
        }
        if(duplicates.size()==0)
            duplicates.add(-1);

        return duplicates;
    }
}