package set39;


import java.util.*;

 class ShortestPath {

        static int[] drr = {-1, 0, 1, 0}; // row direction for moves
        static int[] drc = {0, 1, 0, -1}; // column direction for moves

        static int shortestPath(char[][] matrix, int sr, int sc, int dr, int dc) {
            int n = matrix.length; // number of rows
            int m = matrix[0].length; // number of columns

            boolean[][] visited = new boolean[n][m]; // keep track of visited cells
            Queue<int[]> q = new LinkedList<>(); // queue for BFS

            q.add(new int[] {sr, sc, 0}); // add source cell to queue with distance 0
            visited[sr][sc] = true; // mark source cell as visited

            while (!q.isEmpty()) {
                int[] curr = q.poll(); // get the next cell from the queue

                if (curr[0] == dr && curr[1] == dc) {
                    return curr[2]; // found the destination cell, return its distance from the source
                }

                // try all possible moves from the current cell
                for (int i = 0; i < 4; i++) {
                    int nr = curr[0] + drr[i]; // new row after move
                    int nc = curr[1] + drc[i]; // new column after move

                    if (nr >= 0 && nr < n && nc >= 0 && nc < m && matrix[nr][nc] != '0' && !visited[nr][nc]) {
                        // if the new cell is valid and not visited yet, add it to the queue with distance + 1
                        q.add(new int[] {nr, nc, curr[2] + 1});
                        visited[nr][nc] = true; // mark the new cell as visited
                    }
                }
            }

            return -1; // destination cell not reachable
        }

        public static void main(String[] args) {
            char[][] matrix = {
                    {'0', '*', '0', 's'},
                    {'*', '0', '*', '*'},
                    {'0', '*', '*', '*'},
                    {'d', '*', '*', '*'}
            };
            int sr = 0, sc = 3, dr = 3, dc = 0;
            int dist = shortestPath(matrix, sr, sc, dr, dc);
            System.out.println(dist); // prints 6

            char[][] matrix2 = {
                    {'0', '*', '0', 's'},
                    {'*', '0', '*', '*'},
                    {'0', '*', '*', '*'},
                    {'d', '0', '0', '0'}
            };
            int dist2 = shortestPath(matrix2, sr, sc, dr, dc);
            System.out.println(dist2); // prints -1
        }
    }
