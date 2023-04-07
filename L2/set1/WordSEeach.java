public class t {
    static int[][] dir  = {{1,0},{0,1},{-1,0},{0,-1},{1,1},{-1,-1},{-1,1},{1,-1}};


    public static void WordSEeach(String args[])
    {
        int R = 3;
        int C = 13;
        char[][] grid = { { 'G', 'E', 'E', 'K', 'S', 'F', 'O', 'R', 'G', 'E', 'E', 'K', 'S' },
                { 'G', 'E', 'E', 'K', 'S', 'Q', 'U', 'I', 'Z', 'G', 'E', 'E', 'K' },
                { 'I', 'D', 'E', 'Q', 'A', 'P', 'R', 'A', 'C', 'T', 'I', 'C', 'E' } };
        patternSearch(grid, "GEEKS");
        System.out.println();
        patternSearch(grid, "EEE");
    }

    private static void patternSearch(char[][] grid, String geeks) {

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<13;j++)
            {
                if(grid[i][j]==geeks.charAt(0)&&bsf(i,j,0,grid,geeks))
                {
                    System.out.println("index "+i +"  "+j);
                }
            }

        }
    }

    private static boolean bsf(int i, int j, int ind, char[][] grid, String geeks) {
        if(i<0 ||j<0 ||i==3||j==13||grid[i][j]!=geeks.charAt(ind)&&grid[i][j]!='*')
            return false;
        if(ind == geeks.length() - 1) {
            return true;
        }
        char c = grid[i][j];
        grid[i][j] = '*';
        boolean flag = false;

        for(int[] d: dir) {
            int nextI = i + d[0];
            int nextJ = j + d[1];
            flag = flag || bsf(nextI, nextJ, ind + 1, grid, geeks);
        }

        grid[i][j] = c;
        return flag;
    }
}
