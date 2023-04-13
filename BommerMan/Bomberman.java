package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Bomberman {
    private static final int ROW = 13;
    private static final int COLUMN = 15;
    private static final int TIME = 3;
    private static final int MAX_ENEMIES = 5;

    private static char[][] grid = new char[ROW][COLUMN];
    private static int[][] bombs = new int[ROW][COLUMN];
    private static int timer = TIME;
    private static int score = 0;
    private static int lives = 3;
    private static boolean gameover = false;
    private static int numEnemies = 0;
    private static int bombRange = 2;
    private static int bombTimer = 3;

    private static List<Enemy> enemies = new ArrayList<>();
    private static Random random = new Random();

    private static class Enemy {
        int row;
        int col;
        boolean dead;

        Enemy(int row, int col) {
            this.row = row;
            this.col = col;
            this.dead = false;
        }
    }

    public static void main(String[] args) {
        initializeGrid();

        Scanner scanner = new Scanner(System.in);
        while (!gameover) {
            printGrid();

            System.out.println("Enter command (u/d/l/r/p): ");
            String input = scanner.nextLine();
            if (input.equals("p")) {
                if (timer == 0) {
                    explode();
                } else {
                    plantBomb();
                }
                timer = TIME;
            } else {
                movePlayer(input);
                decrementTimer();
            }

            // Move enemies
            for (Enemy enemy : enemies) {
                if (!enemy.dead) {
                    moveEnemy(enemy);
                }
            }

            // Check for game over
            if (lives == 0) {
                gameover = true;
                System.out.println("Game over!");
            }
        }
    }

    private static void initializeGrid() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (i == 0 || i == ROW - 1 || j == 0 || j == COLUMN - 1 || (i % 2 == 0 && j % 2 == 0)) {
                    grid[i][j] = '#';
                } else {
                    grid[i][j] = ' ';
                }
            }
        }

        grid[1][1] = 'P';

        // Add enemies
        while (numEnemies < MAX_ENEMIES) {
            int row = random.nextInt(ROW - 2) + 1;
            int col = random.nextInt(COLUMN - 2) + 1;

            if (grid[row][col] != '#') {
                enemies.add(new Enemy(row, col));
                numEnemies++;
            }
        }
    }

    private static void printGrid() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Score: " + score);
        System.out.println("Lives: " + lives);
    }

    private static void movePlayer(String input) {
        int row = -1, col = -1;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (grid[i][j] == 'P') {
                    row = i;
                    col = j;
                    break;
                }
            }
            if (row != -1 && col != -1) {
                break;
            }
        }

        int newRow = row, newCol = col;
        switch (input) {
            case "u":
                newRow = row - 1;
                break;
            case "d":
                newRow = row + 1;
                break;
            case "l":
                newCol = col - 1;
                break;
            case "r":
                newCol = col + 1;
                break;
        }

        // Check if the new position is within the bounds of the grid
        if (newRow >= 0 && newRow < ROW && newCol >= 0 && newCol < COLUMN) {
            if (grid[newRow][newCol] == ' ') {
                grid[row][col] = ' ';
                grid[newRow][newCol] = 'P';

                // Update player position
                row = newRow;
                col = newCol;

                // Check for collisions with enemies
                for (Enemy enemy : enemies) {
                    if (!enemy.dead && row == enemy.row && col == enemy.col) {
                        lives--;
                        if (lives == 0) {
                            gameover = true;
                            System.out.println("Game over!");
                        } else {
                            initializeGrid();
                        }
                        break;
                    }
                }
            }
        }
    }


    private static void moveEnemy(Enemy enemy) {
        int row = enemy.row;
        int col = enemy.col;

        int direction = random.nextInt(4); // 0 = up, 1 = down, 2 = left, 3 = right

        switch (direction) {
            case 0:
                if (row > 0 && grid[row - 1][col] == ' ') {
                    grid[row][col] = ' ';
                    grid[row - 1][col] = 'E';
                    enemy.row = row - 1;
                }
                break;
            case 1:
                if (row < ROW - 1 && grid[row + 1][col] == ' ') {
                    grid[row][col] = ' ';
                    grid[row + 1][col] = 'E';
                    enemy.row = row + 1;
                }
                break;
            case 2:
                if (col > 0 && grid[row][col - 1] == ' ') {
                    grid[row][col] = ' ';
                    grid[row][col - 1] = 'E';
                    enemy.col = col - 1;
                }
                break;
            case 3:
                if (col < COLUMN - 1 && grid[row][col + 1] == ' ') {
                    grid[row][col] = ' ';
                    grid[row][col + 1] = 'E';
                    enemy.col = col + 1;
                }
                break;
        }

        // Check for collisions with player
        if (row == getPlayerRow() && col == getPlayerCol()) {
            lives--;
            if (lives == 0) {
                gameover = true;
                System.out.println("Game over!");
            } else {
                initializeGrid();
            }
        }
    }


    private static void decrementTimer() {
        if (timer > 0) {
            timer--;
        }
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (bombs[i][j] > 0) {
                    bombs[i][j]--;
                    if (bombs[i][j] == 0) {
                        explodeBomb(i, j);
                    }
                }
            }
        }
    }

    private static void plantBomb() {
        int row = getPlayerRow();
        int col = getPlayerCol();

        // Check if the current position is empty and the player has bombs available
        if (grid[row][col] == ' ' && bombs[row][col] == 0) {
            // Plant the bomb
            bombs[row][col] = bombTimer;
            grid[row][col] = 'B';
        }
    }


    private static void explode() {
        int row = -1, col = -1;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (grid[i][j] == 'P') {
                    row = i;
                    col = j;
                    break;
                }
            }
            if (row != -1 && col != -1) {
                break;
            }
        }

        explodeBomb(row, col);
    }

    private static void explodeBomb(int row, int col) {
        grid[row][col] = ' ';

        for (int i = 1; i <= bombRange; i++) {
            if (row - i >= 0 && grid[row - i][col] != '#') {
                if (grid[row - i][col] == 'E') {
                    for (Enemy enemy : enemies) {
                        if (enemy.row == row - i && enemy.col == col) {
                            enemy.dead = true;
                            score++;
                            break;
                        }
                    }
                }
                grid[row - i][col] = ' ';
            }

            if (row + i < ROW && grid[row + i][col] != '#') {
                if (grid[row + i][col] == 'E') {
                    for (Enemy enemy : enemies) {
                        if (enemy.row == row + i && enemy.col == col) {
                            enemy.dead = true;
                            score++;
                            break;
                        }
                    }
                }
                grid[row + i][col] = ' ';
            }

            if (col - i >= 0 && grid[row][col - i] != '#') {
                if (grid[row][col - i] == 'E') {
                    for (Enemy enemy : enemies) {
                        if (enemy.row == row && enemy.col == col - i) {
                            enemy.dead = true;
                            score++;
                            break;
                        }
                    }
                }
                grid[row][col - i] = ' ';
            }

            if (col + i < COLUMN && grid[row][col + i] != '#') {
                if (grid[row][col + i] == 'E') {
                    for (Enemy enemy : enemies) {
                        if (enemy.row == row && enemy.col == col + i) {
                            enemy.dead = true;
                            score++;
                            break;
                        }
                    }
                }
                grid[row][col + i] = ' ';
            }
        }
    }
    private static int getPlayerRow() {
        int row = -1;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (grid[i][j] == 'P') {
                    row = i;
                    break;
                }
            }
            if (row != -1) {
                break;
            }
        }
        return row;
    }

    private static int getPlayerCol() {
        int col = -1;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (grid[i][j] == 'P') {
                    col = j;
                    break;
                }
            }
            if (col != -1) {
                break;
            }
        }
        return col;
    }


}
