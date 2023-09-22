package BommerMan;

import java.util.*;

 class BombermanGame {
    private int numRows;
    private int numCols;
    private char[][] grid;
    private List<Bomb> bombs;
    private Player player;
    private List<Enemy> enemies;

    public BombermanGame(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.grid = new char[numRows][numCols];
        this.bombs = new ArrayList<>();
        this.player = new Player(0, 0);
        this.enemies = new ArrayList<>();
    }

    public void initializeGame() {
        // Initialize grid with walls and empty spaces
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (i == 0 || i == numRows - 1 || j == 0 || j == numCols - 1) {
                    grid[i][j] = '#'; // wall
                } else {
                    grid[i][j] = ' '; // empty space
                }
            }
        }

        // Place player and enemies randomly on grid
        Random rand = new Random();
        int playerRow = rand.nextInt(numRows - 2) + 1; // exclude walls
        int playerCol = rand.nextInt(numCols - 2) + 1;
        player.setRow(playerRow);
        player.setCol(playerCol);
        grid[playerRow][playerCol] = 'P'; // player symbol
        for (int i = 0; i < 3; i++) { // 3 enemies
            int enemyRow = rand.nextInt(numRows - 2) + 1;
            int enemyCol = rand.nextInt(numCols - 2) + 1;
            while (grid[enemyRow][enemyCol] != ' ') { // keep trying until empty space found
                enemyRow = rand.nextInt(numRows - 2) + 1;
                enemyCol = rand.nextInt(numCols - 2) + 1;
            }
            Enemy enemy = new Enemy(enemyRow, enemyCol);
            enemies.add(enemy);
            grid[enemyRow][enemyCol] = 'E'; // enemy symbol
        }
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
// Move player
            printBoard();
            System.out.println("Enter direction to move (WASD):");
            String input = scanner.nextLine();
            char direction = input.charAt(0);
            int newRow;
            int newCol;
            switch (direction) {
                case 'W':
                    newRow = player.getRow() - 1;
                    newCol = player.getCol();
                    break;
                case 'A':
                    newRow = player.getRow();
                    newCol = player.getCol() - 1;
                    break;
                case 'S':
                    newRow = player.getRow() + 1;
                    newCol = player.getCol();
                    break;
                case 'D':
                    newRow = player.getRow();
                    newCol = player.getCol() + 1;
                    break;
                default:
                    continue; // invalid input, try again
            }
            if (grid[newRow][newCol] == ' ') { // empty space
                grid[player.getRow()][player.getCol()] = ' '; // clear old position
                player.setRow(newRow);
                player.setCol(newCol);
                grid[newRow][newCol] = 'P'; // update new position
            } else if (grid[newRow][newCol] == 'E') { // enemy
                System.out.println("Game over! You were caught by an enemy.");
                break;
            }

            // Move enemies
             for (Enemy enemy : enemies) {
                int enemyRow = enemy.getRow();
                int enemyCol = enemy.getCol();
                int playerRow = player.getRow();
                int playerCol = player.getCol();
                 newRow = enemyRow;
                 newCol = enemyCol;
                if (playerRow < enemyRow) { // move up
                    newRow--;
                } else if (playerRow > enemyRow) { // move down
                    newRow++;
                } else if (playerCol < enemyCol) { // move left
                    newCol--;
                } else if (playerCol > enemyCol) { // move right
                    newCol++;
                }
                if (grid[newRow][newCol] == ' ') { // empty space
                    grid[enemyRow][enemyCol] = ' '; // clear old position
                    enemy.setRow(newRow);
                    enemy.setCol(newCol);
                    grid[newRow][newCol] = 'E'; // update new position
                } else if (grid[newRow][newCol] == 'P') { // player
                    System.out.println("Game over! An enemy caught you.");
                    break;
                }
            }
            // Check if player can drop a bomb
            if (bombs.size() < 1) { // player can only have one bomb at a time
                System.out.println("Press B to drop a bomb:");
                String input2 = scanner.nextLine();
                if (input2.charAt(0) == 'B') {
                    Bomb bomb = new Bomb(player.getRow(), player.getCol());
                    bombs.add(bomb);
                }
            }
            // Update bombs
            for (int i = 0; i < bombs.size(); i++) {
                Bomb bomb = bombs.get(i);
                if (bomb.getCountdown() == 0) { // bomb explodes
                    int bombRow = bomb.getRow();
                    int bombCol = bomb.getCol();
                    grid[bombRow][bombCol] = ' '; // clear bomb position
                    for (int j = 0; j < enemies.size(); j++) {
                        Enemy enemy = enemies.get(j);
                        int enemyRow = enemy.getRow();
                        int enemyCol = enemy.getCol();
                        if (enemyRow == bombRow && enemyCol == bombCol) { // enemy caught in explosion
                            enemies.remove(j);
                            grid[enemyRow][enemyCol] = ' '; // clear enemy position
                        }
                    }
                    bombs.remove(i);
                } else { // decrement countdown
                    bomb.decrementCountdown();
                }
            }

            // Check if player has won
            if (enemies.size() == 0) {
                System.out.println("Congratulations! You have defeated all enemies.");
                break;
            }

            // Print grid
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
     public void printBoard() {
         for (int i = 0; i < numRows; i++) {
             for (int j = 0; j < numCols; j++) {
                 System.out.print(grid[i][j] + " ");
             }
             System.out.println();
         }
         System.out.println();
     }


     public static void main(String[] args) {
        BombermanGame game = new BombermanGame(10, 10);
        game.initializeGame();
        game.printBoard();
        game.playGame();
    }
}

class Player {
    private int row;
    private int col;
    public Player(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}

class Enemy {
    private int row;
    private int col;
    public Enemy(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}

class Bomb {
    private int row;
    private int col;
    private int countdown;
    public Bomb(int row, int col) {
        this.row = row;
        this.col = col;
        this.countdown = 3;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getCountdown() {
        return countdown;
    }

    public void decrementCountdown() {
        countdown--;
    }
}






