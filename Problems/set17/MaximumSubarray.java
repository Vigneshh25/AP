package Problems.set17;

public class MaximumSubarray {

        // Driver Code
        public static void main(String[] args)
        {
            int[] a = { -2, -3, 4, -1, -2, 1, 5, -3 };
            System.out.println("Maximum contiguous sum is "
                    + maxSubArraySum(a));
        }

        // Function Call
        static int maxSubArraySum(int a[])
        {
            int size = a.length;
            int max_so_far = Integer.MIN_VALUE, max_ending_here
                    = 0;

            for (int j : a) {
                max_ending_here = max_ending_here + j;
                if (max_so_far < max_ending_here)
                    max_so_far = max_ending_here;
                if (max_ending_here < 0)
                    max_ending_here = 0;
            }
            return max_so_far;
        }
    }


