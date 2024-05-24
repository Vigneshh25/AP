package Problems.set38;

import java.util.Scanner;

public class KNight {

        public static void main(String[] args) {
            // Read the positions of the knights from the user
            Scanner scanner = new Scanner(System.in);
            int[][] knights = new int[9][2];
            for (int i = 0; i < 9; i++) {
                knights[i][0] = scanner.nextInt();
                knights[i][1] = scanner.nextInt();
            }
            // Check if the configuration is valid
            System.out.println(isValid(knights) ? "Valid" : "Invalid");
        }

        // Check if the given configuration of knights is valid
        public static boolean isValid(int[][] knights) {
            for (int i = 0; i < 9; i++) {
                // Check if there are any other knights in the attack range of the current knight
                for (int j = 0; j < 9; j++) {
                    if (i != j && isInAttackRange(knights[i], knights[j])) {
                        return false;
                    }
                }
            }
            return true;
        }

        // Check if the second knight is in the attack range of the first knight
        public static boolean isInAttackRange(int[] knight1, int[] knight2) {
            int x1 = knight1[0];
            int y1 = knight1[1];
            int x2 = knight2[0];
            int y2 = knight2[1];
            return (Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 1) || (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 2);
        }


}
