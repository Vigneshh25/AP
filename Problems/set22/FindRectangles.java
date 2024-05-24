package Problems.set22;

import java.util.ArrayList;
import java.util.List;

public class FindRectangles {

    public static int countRectangles(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int count = 0;

    for (int i = 0; i < m; i++) {
        for (int j = i + 1; j < m; j++) {
            for (int k = 0; k < n; k++) {
                boolean valid = true;
                for (int l = i; l <= j; l++) {
                    if (matrix[l][k] != 1) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    count++;
                }
            }
        }
    }

    return count;
}


    public static List<Point> caputreCoordinates(int matrix[][], int i, int j) {

        int colStart = j;
        Point startPoint = new Point(i, j);

        while (j < matrix[0].length && matrix[i][j] == 0) {
            matrix[i][j] = 1;
            j++;
        }

        int colEnd = --j;
        i++;
        j = colStart;
        while (i < matrix.length && matrix[i][j] == 0) {
            while (j <= colEnd) {
                matrix[i][j] = 1;
                j++;
            }
            j = colStart;
            i++;
        }

        Point endPoint = new Point(--i, colEnd);

        List<Point> listPoint = new ArrayList<Point>();
        listPoint.add(startPoint);
        listPoint.add(endPoint);

        return listPoint;
    }

    public static void main(String[] args) {
        int matrix[][] = {{0, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 1, 1},
                {1, 1, 1, 0, 0, 1, 1},
                {1, 1, 1, 0, 0, 1, 1},
                {1, 1, 1, 0, 0, 1, 1},
                {1, 1, 1, 0, 0, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1}};
        System.out.println(countRectangles(matrix));
    }

}
class Point {
    int x = -1;
    int y = -1;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

}

