public class WordSearch {

    public boolean exits(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int ind = 0;
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(board[i][j]==word.charAt(ind) && seachNext(board,word,ind,i,j,m,n))
                    return true;
            }
        }
        return false;
    }
    boolean seachNext(char[][] board,String word,int ind,int i,int j, int m,int n)
    {
        if(ind==word.length())
            return true;
        if(i<0 || j<0 || i==m || j==n ||
                board[i][j]!=word.charAt(ind)||board[i][j]=='*')
            return false;
        char c = board[i][j];
        board[i][j] = '*';

        if(seachNext(board,word,ind+1,i+1,j,m,n)
                ||seachNext(board,word,ind+1,i,j+1,m,n))
            return true;
        board[i][j] = c;
        return false;
    }
    public static void main(String[] args) {
        char[][] grid = {{'a', 'z', 'o', 'l'},
                {'n', 'x', 'h', 'o'},
                {'v', 'y', 'i', 'v'},
                {'o', 'r', 's', 'e'}};
        Set<String> dictionary = new HashSet<>(Arrays.asList("van", "zoho", "love", "are", "is"));
        WordSearch ws = new WordSearch();
        System.out.println(ws.exits(grid, "zoho"));
    }

}
