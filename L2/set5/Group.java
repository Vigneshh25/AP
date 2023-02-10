package set5;

import java.util.Arrays;

public class Group {

        // Define the array and the value of X
        static int[] array = {3, 9, 7, 4, 6, 8};
        static int X = 3;

        // Define a 2D array to store the results of the subproblems
        static int[][] dp;

        public static void main(String[] args) {
            // Initialize the 2D array with -1 to indicate that the subproblems have not been solved yet
            dp = new int[array.length][X];
            for (int[] row : dp) {

                Arrays.fill(row, -1);
            }

            // Initialize a counter for the number of groups
            int num_groups = 0;

            // Iterate through the array and find the number of groups starting at each element
            for (int i = 0; i < array.length; i++) {
                num_groups += findGroups(i, 0);
            }

            // Print the number of groups
            System.out.println("Number of groups: " + num_groups);
        }

        // A recursive function to find the number of groups starting at the given index and with the given sum
        static int findGroups(int index, int sum) {
            // If the index is out of bounds or the sum is not divisible by X, return 0
            if (index >= array.length || sum % X != 0) {
                return 0;
            }

            // If the subproblem has already been solved, return the result from the 2D array
            if (dp[index][sum] != -1) {
                return dp[index][sum];
            }

            // Initialize a counter for the number of groups
            int num_groups = 0;

            // Consider adding the current element to the group
            num_groups += findGroups(index + 1, (sum + array[index]) % X);

            // Consider not adding the current element to the group
            num_groups += findGroups(index + 1, sum);

            // Store the result in the 2D array and return it
            return dp[index][sum] = num_groups;
        }

}
