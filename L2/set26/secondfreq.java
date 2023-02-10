package set26;

public class secondfreq {

        public static void main(String[] args) {
            // The series of numbers
            int[] numbers = {1, 2, 3, 4, 5, 4, 3, 2, 1, 3, 4};

            // Initialize variables to store the most and second most frequent numbers
            int max1 = 0;
            int max2 = 0;
            int max1Count = 0;
            int max2Count = 0;

            // Iterate through the series and count the frequency of each number
            for (int number : numbers) {
                int count = 0;
                for (int n : numbers) {
                    if (number == n) {
                        count++;
                    }
                }

                // Update max1 and max2 as needed
                if (count > max1Count) {
                    max2 = max1;
                    max2Count = max1Count;
                    max1 = number;
                    max1Count = count;
                } else if (count > max2Count && count < max1Count) {
                    max2 = number;
                    max2Count = count;
                }
            }

            System.out.println("The second most frequent number is: " + max2);
        }
    }


