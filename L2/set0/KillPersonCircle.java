package set0;

import java.util.ArrayList;
import java.util.List;

class KillPersonCircle{


    static void Josh(List<Integer> person, int k, int index)
    {

        // Base case , when only one person is left
        if (person.size() == k) {
            return;
        }

        // find the index of first person which will die
        index = ((index + k) % person.size());
        System.out.println(person.get(index));

        // remove the first person which is going to be killed
        person.remove(index);

        // recursive call for n-1 persons
        Josh(person, k, index);
    }

    // Driver code
    public static void main(String [] args)
    {
        int n = 9; // specific n and k values for original
        // josephus problem
        int k = 4;
        k--; // (k-1)th person will be killed
        int index = 0; // The index where the person which will die

        List<Integer> person = new ArrayList<>();

        // fill the person vector
        for (int i = 1; i <= n; i++) {
            person.add(i);
        }

        Josh(person, k, index);
    }
}

// This code is contributed by umadevi9616
