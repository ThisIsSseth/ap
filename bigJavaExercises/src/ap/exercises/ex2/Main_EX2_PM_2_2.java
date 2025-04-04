package ap.exercises.ex2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_2_2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        byte c = 0;
        System.out.println("Checking if there are any saved games...");
        if (board.memoryChecker()) {
            board.boardReader();
            c = board.getK();
            System.out.println("Loading Saved game...");
        } else {
            System.out.println("No saved memory.\nStarting a new game...");
            System.out.println("enter the dimension (2-127):");
            byte k = sc.nextByte();
            if (k < 1) {
                System.out.println("Invalid number");
            } else {
                board.createBoard(k);
                System.out.println("enter a  of dots (1-127):");
                c = sc.nextByte();
                while (c >= k * k || c < 0) {
                    System.out.println("out of range! try again:");
                    c = sc.nextByte();
                }
                board.dotter(c);
            }
        }
        board.print();
        byte move = 0;
        long startTime = System.currentTimeMillis();

        while (board.getNumOfDot() > 0 && move != 4) {

            System.out.println("enter a move:\n0.up\n1.right\n2.down\n3.left\n4. Save and Exit");
            move = sc.nextByte();
            if (move > -1 && move < 4) {
                board.xMove(move);
                board.print();
            } else if (move == 4) {
                board.boardSaver(System.currentTimeMillis() - startTime);
                System.out.println("Saved!");
                System.exit(0);
            } else {
                System.out.println("invalid move");
            }

        }
        long endTime = System.currentTimeMillis();
        if (c == board.numOfDot) {
            board.memoryDelete();
        }
        System.out.println(endTime - startTime);
        System.out.println("You scored " + board.getNumOfDot() + " dots!");

        sc.close();
    }

    //--------------------------------------

    static class Board {
        private byte K;
        private byte[][] board;
        private byte xPosX = 1, xPosY = 1;
        private byte numOfDot;
        //wall = 1, pacman(X) = 2, dot = -1
        File gameState;
        long playingTime = 0;

        public void createBoard(byte k) {
            K = k;
            board = new byte[k + 2][k + 2];
            for (int i = 0; i < k + 2; i++) {
                for (int j = 0; j < k + 2; j++) {
                    if (i == 0 || j == 0 || i == k + 1 || j == k + 1) {
                        board[i][j] = 1;
                    } else {
                        board[i][j] = 0;
                    }
                }
            }
            board[1][1] = 2;

        }

        public void dotter(byte c) {
            numOfDot = c;
            Random rand = new Random();
            int intRandRow, intRandCol;
            while (c > 0) {
                intRandRow = rand.nextInt(K) + 1;
                intRandCol = rand.nextInt(K) + 1;
                if (board[intRandRow][intRandCol] != -1 || !(intRandRow == 1 && intRandCol == 1)) {
                    board[intRandRow][intRandCol] = -1;
                    c--;
                }
            }
        }

        public void print() throws IOException {
            for (int i = 0; i < K + 2; i++) {
                for (int j = 0; j < K + 2; j++) {
                    if (board[i][j] == 1) {
                        System.out.print("*");
                    } else if (board[i][j] == 0) {
                        System.out.print(" ");
                    } else if (board[i][j] == -1) {
                        System.out.print('.');
                    } else {
                        System.out.print("X");
                    }
                }
                System.out.println();
            }
        }

        public void xMove(byte move) throws IOException {
            switch (move) {
                case 0:
                    move = 'w';
                    break;
                case 2:
                    move = 's';
                    break;
                case 3:
                    move = 'd';
                    break;
                case 1:
                    move = 'a';
                    break;
            }
            switch (move) {
                case 'w':
                    if (xPosY != 1) {
                        board[xPosY][xPosX] = 0;
                        xPosY--;
                        dotChecker();
                        board[xPosY][xPosX] = 2;
                        System.out.println("UP");
                    } else
                        System.out.println("hitting a wall!");
                    break;
                case 'a':
                    if (xPosX != 1) {
                        board[xPosY][xPosX] = 0;
                        xPosX--;
                        dotChecker();
                        board[xPosY][xPosX] = 2;
                        System.out.println("LEFT");
                    } else
                        System.out.println("hitting a wall!");
                    break;
                case 's':
                    if (xPosY != K) {
                        board[xPosY][xPosX] = 0;
                        xPosY++;
                        dotChecker();
                        board[xPosY][xPosX] = 2;
                        System.out.println("DOWN");
                    } else
                        System.out.println("hitting a wall!");
                    break;
                case 'd':
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
                numOfDot--;
            }
        }

        public byte getNumOfDot() {
            return numOfDot;
        }

        public byte getK() {
            return K;
        }


        private void boardSaver(long time) throws IOException {
            playingTime += time;
            try (FileWriter writer = new FileWriter("GameState.txt");) {
                writer.write(K + "," + numOfDot + "," + xPosX + "," + xPosY + ',' + playingTime + "\n");
                for (byte[] row : board) {
                    for (byte c : row) {
                        writer.write(c + ",");
                    }
                    writer.write("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void boardReader() {
            String line;
            String[] eachLine;
            try (FileReader fr = new FileReader("GameState.txt"); Scanner scanner = new Scanner(fr);) {
                line = scanner.nextLine();
                eachLine = line.split(",");
                K = Byte.parseByte(eachLine[0]);
                board = new byte[K + 2][K + 2];
                numOfDot = Byte.parseByte(eachLine[1]);
                xPosX = Byte.parseByte(eachLine[2]);
                xPosY = Byte.parseByte(eachLine[3]);
                try {
                    playingTime = Long.parseLong(eachLine[4]);
                } catch (NumberFormatException e) {
                }
                createBoard(K);
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
                e.printStackTrace();
            }

        }

        public void memoryDelete() {
            try {
                Files.delete(Paths.get("GameState.txt"));  // Using NIO (Java 7+)
                System.out.println("File deleted successfully!");
            } catch (IOException e) {
                System.err.println("Failed to delete file: " + e.getMessage());
            }
        }

        public boolean memoryChecker() throws IOException {
            return Files.exists(Paths.get("GameState.txt")) && Files.size(Paths.get("GameState.txt")) > 0;
        }
    }
}

