package ap.exercises.ex2;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class PacmanEngine {
    private final int K;
    private final byte wall = 1;
    private byte[][] board;
    private byte xPosX = 1, xPosY = 1;
    private final int initialNumOfDot;
    private int currentNumOfDot = 1;
    //wall = 1, pacman(X) = 2, dot = -1
    File gameState;
    long playingTime = 0;
    long startTime = System.currentTimeMillis();

    public PacmanEngine(int k, int c) {
        K = k;
        board = new byte[k + 2][k + 2];
        initialNumOfDot = c;
        if (Files.exists(Paths.get("GameState(1).txt"))) {
            boardReader();
        }
        else {
            createBoard();
            dotter();
        }
    }

    private void createBoard() {
        for (int i = 0; i < K + 2; i++) {
            for (int j = 0; j < K + 2; j++) {
                if (i == 0 || j == 0 || i == K + 1 || j == K + 1) {
                    board[i][j] = wall;
                } else {
                    board[i][j] = 0;
                }
            }
        }
        board[1][1] = 2;
    }

    private void dotter() {
        Random rand = new Random();
        int intRandRow, intRandCol, c = initialNumOfDot;
        while (c > 0) {
            intRandRow = rand.nextInt(K) + 1;
            intRandCol = rand.nextInt(K) + 1;
            if (board[intRandRow][intRandCol] != -1 || (intRandRow != 1 && intRandCol != 1)) {
                board[intRandRow][intRandCol] = -1;
                c--;
            }
        }
    }


    public void printMatrix() {
            currentNumOfDot = 0;
            for (int i = 0; i < K + 2; i++) {
                for (int j = 0; j < K + 2; j++) {
                    if (board[i][j] == wall) {
                        System.out.print("*");
                    } else if (board[i][j] == 0) {
                        System.out.print(" ");
                    } else if (board[i][j] == -1) {
                        System.out.print('.');
                        currentNumOfDot++;
                    } else {
                        System.out.print("X");
                    }
                }
                System.out.println();
            }

        if (currentNumOfDot == 0) {
            System.out.println("You wom!");
            memoryDelete();
            System.exit(0);
        }
    }

    public void printScore() {
        System.out.println(initialNumOfDot - currentNumOfDot);
    }

    public void printRemainTime() {
        playingTime += System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        System.out.println(playingTime);
    }

    public void move(int direction) {
        switch (direction) {
            case 0:
                if (xPosY != 1) {
                    board[xPosY][xPosX] = 0;
                    xPosY--;
                    dotChecker();
                    board[xPosY][xPosX] = 2;
                    System.out.println("UP");
                } else
                    System.out.println("hitting a wall!");
                break;
            case 3:
                if (xPosX != 1) {
                    board[xPosY][xPosX] = 0;
                    xPosX--;
                    dotChecker();
                    board[xPosY][xPosX] = 2;
                    System.out.println("LEFT");
                } else
                    System.out.println("hitting a wall!");
                break;
            case 2:
                if (xPosY != K) {
                    board[xPosY][xPosX] = 0;
                    xPosY++;
                    dotChecker();
                    board[xPosY][xPosX] = 2;
                    System.out.println("DOWN");
                } else
                    System.out.println("hitting a wall!");
                break;
            case 1:
                if (xPosX != K) {
                    board[xPosY][xPosX] = 0;
                    xPosX++;
                    dotChecker();
                    board[xPosY][xPosX] = 2;
                    System.out.println("RIGHT");
                } else
                    System.out.println("hitting a wall!");
                break;
        }
    }

    private void dotChecker() {
        if (board[xPosY][xPosX] == -1) {
            currentNumOfDot--;
        }
    }

    public void save() {
        try (FileWriter writer = new FileWriter("GameState(1).txt");) {
            writer.write(currentNumOfDot + "," + xPosX + "," + xPosY + ',' + playingTime + "\n");
            for (byte[] row : board) {
                for (byte c : row) {
                    writer.write(c + ",");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
        }
    }

    private void boardReader() {
        String line;
        String[] eachLine;
        try (FileReader fr = new FileReader("GameState(1).txt"); Scanner scanner = new Scanner(fr);) {
            line = scanner.nextLine();
            eachLine = line.split(",");
            currentNumOfDot = Integer.parseInt(eachLine[0]);
            xPosX = Byte.parseByte(eachLine[1]);
            xPosY = Byte.parseByte(eachLine[2]);
            try {
                playingTime = Long.parseLong(eachLine[3]);
            } catch (NumberFormatException e) {
            }
            for (int i = 0; i < K + 2; i++) {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    eachLine = line.split(",");
                    for (int j = 0; j < K + 2; j++) {
                        board[i][j] = Byte.parseByte(eachLine[j]);
                    }

                }
            }
        } catch (IOException e) {
        }
    }

    public void memoryDelete() {
        try {
            Files.delete(Paths.get("GameState(1).txt"));  // Using NIO (Java 7+)
            System.out.println("File deleted successfully!");
        } catch (IOException e) {
            System.err.println("Failed to delete file: " + e.getMessage());
        }
    }
}