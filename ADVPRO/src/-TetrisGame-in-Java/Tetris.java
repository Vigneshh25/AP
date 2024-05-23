import java.util.*;
public class Tetris{
    public static void main(String[] args) {

        // Module 1 : create 18x10 Matrix
        char matrix[][] = new char[18][10];

        Function.initialFill(matrix);
        Function.printMatrix(matrix);
        int score=0;
        List<String> shape = Arrays.asList("S","L","T","SQ","Z","ML","I");
        Random random = new Random();
        
        while(true)
        {
            int n = random.nextInt(shape.size());
            String ch = shape.get(n);
            char[][] shapeMatrix = Shape.getShape(ch);
            PlayTheGame play = new PlayTheGame(shapeMatrix,matrix);
            if(play.isGameEnd())
            {
                System.out.println("GAME OVER !!\n"+"Achieved Score is : "+score);
                break;
            }
            play.start();
            do{
                prevScore = score;
                score += Function.calculateScore(matrix,score);
            }while(prevScore!=score);  // just if the grid has 3row score then eliminates all three..
            System.out.println("Current Score : "+score);
            Function.printMatrix(matrix);
        }
        
        
    }
}
