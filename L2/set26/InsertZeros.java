package set26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    public class InsertZeros {
        public static List<Integer> insertZeros(List<Integer> bits, int K) {
            List<Integer> modifiedBits = new ArrayList<>();
            int count = 0; // Counter for consecutive 1s
            for (int bit : bits) {
                modifiedBits.add(bit);
                if (bit == 1) {
                    count++;
                    if (count == K) {
                        modifiedBits.add(0); // Insert 0 after K consecutive 1s
                        count = 0; // Reset the counter
                    }
                } else {
                    count = 0; // Reset the counter if we encounter a 0
                }
            }
            return modifiedBits;
        }

        public static void main(String[] args) {
            List<Integer> bits = Arrays.asList(1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1);
            int K = 2;
            List<Integer> modifiedBits = insertZeros(bits, K);
            System.out.println(modifiedBits); // Output: [1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1]
        }
    }
