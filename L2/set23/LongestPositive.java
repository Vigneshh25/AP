package set23;

public class LongestPositive {
    // Java program to find longest running


        // Prints longest sequence of
        // positive integers in an array.
    
    static int findLongestConseqSubseq(int nums[], int N)
	{
	   // add your code here
	 Arrays.sort(nums);
        int count =1, max = 1, startIndex=0, endIndex=0;
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]==nums[i-1]+1)
            {
                count++;
            }
            else if(nums[i]!=nums[i-1])
            {
                if(count > max) {
                    max = count;
                    endIndex = i-1;
                    startIndex = endIndex - count + 1;
                }
                count = 1;
            }
        }
        if(count > max) {
            max = count;
            endIndex = nums.length-1;
            startIndex = endIndex - count + 1;
        }
        System.out.println("Longest consecutive subsequence is:");
        for(int i=startIndex;i<=endIndex;i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println("\nStart Index: " + startIndex + "\nEnd Index: " + endIndex);

    
	}
        static void getLongestSeq(int a[], int n)
        {
            // Variables to keep track of maximum
            // length and starting point of
            // maximum length. And same for current
            // length.
            int maxIdx = 0, maxLen = 0, currLen = 0,
                    currIdx = 0;

            for (int k = 0; k < n; k++)
            {
                if (a[k] > 0) {
                    currLen++;
                    // New sequence, store
                    // beginning index.
                    if (currLen == 1)
                        currIdx = k;
                }
                else {
                    if (currLen > maxLen) {
                        maxLen = currLen;
                        maxIdx = currIdx;
                    }
                    currLen = 0;
                }
            }

            if (currLen > maxLen)
            {
                maxLen = currLen;
                maxIdx = currIdx;
            }

            if (maxLen > 0)
            {
                System.out.print("Length " + maxLen);
                System.out.print(",starting index " + maxIdx);
            }
            else
                System.out.println(
                        "No positive sequence detected.");

            return;
        }

        // Driver code
        public static void main(String[] args)
        {
            int arr[] = { 1, 2, -3, 2, 3, 4, -6, 1,
                    2, 3, 4, 5, -8, 5, 6 };
            int n = arr.length;
            getLongestSeq(arr, n);
        }
    }

// This article is contributed by vt_m.
