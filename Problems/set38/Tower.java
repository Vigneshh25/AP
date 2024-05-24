package Problems.set38;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Tower
{
        public static void main(String[] args) {
            // Read the heights of the blocks from the user
            Scanner scanner = new Scanner(System.in);
            int[] blocks = new int[6];
            for (int i = 0; i < 6; i++) {
                blocks[i] = scanner.nextInt();
            }
            // Read the desired heights of the towers from the user
            int tower1 = scanner.nextInt();
            int tower2 = scanner.nextInt();
            // Sort the blocks in ascending order
            Arrays.sort(blocks);
            // Create lists to store the blocks used to construct the towers
            List<Integer> tower1Blocks = new ArrayList<>();
            List<Integer> tower2Blocks = new ArrayList<>();
            // Iterate over the sorted blocks and check if they can be used to construct the towers
            for (int block : blocks) {
                if (tower1Blocks.size() < 3 && tower1 - block >= 0) {
                    tower1Blocks.add(block);
                    tower1 -= block;
                } else if (tower2Blocks.size() < 3 && tower2 - block >= 0) {
                    tower2Blocks.add(block);
                    tower2 -= block;
                }
            }
            // Print the blocks used to construct the towers in ascending order
            for (int block : tower1Blocks) {
                System.out.print(block + " ");
            }
            for (int block : tower2Blocks) {
                System.out.print(block + " ");
            }
        }
    }


