package ap.exercises.ex2;

import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_2_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the dimension (1-127):");
        byte k = sc.nextByte();
        if (k < 0) {
            System.out.println("Invalid number");
        } else {
            Board board = new Board();
            board.createBoard(k);
            System.out.println("enter a  of dots (1-127):");
            byte c = sc.nextByte();
            while (c >= k * k || c < 0) {
                System.out.println("out of range! try again:");
                c = sc.nextByte();
            }
            board.dotter(c);
            board.print();
            byte move;
            long startTime = System.currentTimeMillis();
            while (board.getNumOfDot() > 0) {
                System.out.println("enter a move:\n0.up\n1.right\n2.down\n3.left");
                move = sc.nextByte();
                board.xMove(move);
                board.print();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime);
            System.out.println("You scored " + board.getNumOfDot() + " dots!");
        }
        sc.close();
    }

    //--------------------------------------

    static class Board {
        private byte K;
        private byte[][] board;
        private byte xPosX = 1, xPosY = 1;
        public byte numOfDot;
        //wall = 1, pacman(X) = 2, dot = -1

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
                if (board[intRandRow][intRandCol] != -1 || !(intRandRow == 1 && intRandCol == 1 )) {
                    board[intRandRow][intRandCol] = -1;
                    c--;
                }
            }
        }

        public void print() {
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

        public void xMove(byte move) {
            switch (move) {
                case 0:
                    move = 'w';
                    break;
                case 2:
                    move = 's';
                    break;
                case 3:
                    move = 'a';
                    break;
                case 1:
                    move = 'd';
                    break;
                default:
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
                default:
                    System.out.println("invalid move");
                    break;
            }
        }
        private void dotChecker(){
            if (board[xPosY][xPosX] == -1) {
                numOfDot--;
            }
        }
        public byte getNumOfDot(){
            return numOfDot;
        }
    }
}

