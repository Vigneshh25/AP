package Problems.set42;

import java.util.HashMap;

public class possiblemovequeen {

    // Java program to find number of cells a queen can move
// with obstacles on the chessboard

        static class pair
        {
            int first, second;
            public pair(int first, int second)
            {
                this.first = first;
                this.second = second;
            }
        }
        // Return if position is valid on chessboard
        static boolean range(int n, int x, int y)
        {
            return (x <= n && x > 0 && y <= n && y > 0);
        }

        // Return the number of moves with a given direction
        static int check(int n, int x, int y, int xx, int yy,
                         HashMap<pair, Integer> mp)
        {
            int ans = 0;

            // Checking valid move of Queen in a direction.
            while (range(n, x, y) && ! mp.containsKey(new pair(x, y)))
            {
                x += xx;
                y += yy;
                ans++;
            }

            return ans;
        }

        // Return the number of position a Queen can move.
        static int numberofPosition(int n, int k, int x, int y,
                                    int obstPosx[], int obstPosy[])
        {
            int x1, y1, ans = 0;
            HashMap <pair, Integer> mp = new HashMap<>();

            // Mapping each obstacle's position
            while(k>0)
            {
                k--;
                x1 = obstPosx[k];
                y1 = obstPosy[k];

                mp.put(new pair(x1, y1), 1);
            }

            // Fetching number of position a queen can
            // move in each direction.
            ans += check(n, x + 1, y, 1, 0, mp);
            ans += check(n, x-1, y, -1, 0, mp);
            ans += check(n, x, y + 1, 0, 1, mp);
            ans += check(n, x, y-1, 0, -1, mp);
            ans += check(n, x + 1, y + 1, 1, 1, mp);
            ans += check(n, x + 1, y-1, 1, -1, mp);
            ans += check(n, x-1, y + 1, -1, 1, mp);

            return ans;
        }

        // Driven Program
        public static void main(String[] args)
        {
            int n = 8; // Chessboard size
            int k = 1; // Number of obstacles
            int Qposx = 4; // Queen x position
            int Qposy = 4; // Queen y position
            int obstPosx[] = { 3 }; // x position of obstacles
            int obstPosy[] = { 5 }; // y position of obstacles

            System.out.print(numberofPosition(n, k, Qposx, Qposy,
                    obstPosx, obstPosy) +"\n");
        }
    }

// This code contributed by Rajput-Ji


