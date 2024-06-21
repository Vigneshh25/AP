package snake;

import java.util.*;

public class SnakeGame {
    private GameBoard board;
    private Snake snake;
    private int[][] food;
    private int foodIndex;
    private int score;

    public SnakeGame(int width, int height, int[][] food) {
        this.board = new GameBoard(width, height);
        this.snake = new Snake();
        this.food = food;
        this.foodIndex = 0;
        this.score = 0;
        // Place the initial food
        if (food.length > 0) {
            board.placeFood(food[foodIndex][0], food[foodIndex][1]);
        }
    }

    public int move(String direction) {
        if (snake.move(direction, board)) {
            int[] head = snake.getHead();
            // Check if the snake eats the food
            if (foodIndex < food.length && head[0] == food[foodIndex][0] && head[1] == food[foodIndex][1]) {
                score++;
                foodIndex++;
                snake.grow();
                if (foodIndex < food.length) {
                    board.placeFood(food[foodIndex][0], food[foodIndex][1]);
                }
            }
            board.updateBoard(snake, foodIndex < food.length ? food[foodIndex] : null);
            board.display();
            return score;
        } else {
            System.out.println("Game Over!");
            return -1; // Game over
        }
    }

    public static void main(String[] args) {
        int width = 10;
        int height = 10;
        int[][] food = {{1, 2}, {0, 1}, {2, 2}, {3, 4}};
        SnakeGame game = new SnakeGame(width, height, food);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter move (U, D, L, R): ");
            String direction = scanner.nextLine().trim().toUpperCase();
            int result = game.move(direction);
            if (result == -1) break;
            System.out.println("Score: " + result);
        }
        scanner.close();
    }
}

class GameBoard {
    private int width;
    private int height;
    private char[][] board;

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new char[height][width];
        clearBoard();
    }

    public void clearBoard() {
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                board[r][c] = ' ';
            }
        }
    }

    public void placeFood(int row, int col) {
        board[row][col] = 'F';
    }

    public void updateBoard(Snake snake, int[] foodPosition) {
        clearBoard();
        if (foodPosition != null) {
            placeFood(foodPosition[0], foodPosition[1]);
        }
        for (int[] pos : snake.getBody()) {
            board[pos[0]][pos[1]] = 'S';
        }
    }

    public void display() {
        System.out.println("+" + repeat("-", width) + "+");
        for (int r = 0; r < height; r++) {
            System.out.print("|");
            for (int c = 0; c < width; c++) {
                System.out.print(board[r][c]);
            }
            System.out.println("|");
        }
        System.out.println("+" + repeat("-", width) + "+");
    }

    private String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    public boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= height || col < 0 || col >= width;
    }
}

class Snake {
    private LinkedList<int[]> body;
    private Set<String> bodySet;

    public Snake() {
        body = new LinkedList<>();
        body.add(new int[]{0, 0});
        bodySet = new HashSet<>();
        bodySet.add("0,0");
    }

    public boolean move(String direction, GameBoard board) {
        int[] currentHead = body.peekFirst();
        int newHeadRow = currentHead[0];
        int newHeadCol = currentHead[1];

        switch (direction) {
            case "U":
                newHeadRow--;
                break;
            case "D":
                newHeadRow++;
                break;
            case "L":
                newHeadCol--;
                break;
            case "R":
                newHeadCol++;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        if (board.isOutOfBounds(newHeadRow, newHeadCol) || bodySet.contains(newHeadRow + "," + newHeadCol)) {
            return false;
        }

        body.addFirst(new int[]{newHeadRow, newHeadCol});
        bodySet.add(newHeadRow + "," + newHeadCol);

        int[] tail = body.removeLast();
        bodySet.remove(tail[0] + "," + tail[1]);

        return true;
    }

    public void grow() {
        int[] tail = body.peekLast();
        body.add(tail);
        bodySet.add(tail[0] + "," + tail[1]);
    }

    public int[] getHead() {
        return body.peekFirst();
    }

    public List<int[]> getBody() {
        return body;
    }
}
