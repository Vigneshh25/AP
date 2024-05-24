package Problems.set1;

import java.util.HashSet;
import java.util.Set;

class suduko {
    public static void main(String[] args)
    {
        char[][] board = new char[][] {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '8', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };
        Set<String> set = new HashSet<>();
        for (int i=0;i<9;i++) {
            for (int j = 0; j < 9; j++) {
                if(board[i][j]!='.') {
                    char number = board[i][j];

                    if (!set.add(number + "in row" + i) || !set.add(number + "in col" + j) ||
                            !set.add(number + "in block" + (i / 3) + (j / 3))) {
                        System.out.println(false);
                        break;
                    }
                }
            }
        }

        System.out.println("tre");
    }
}