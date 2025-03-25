package ap.exercises.ex2;

import java.util.Scanner;

public class Main_EX2_PM_1_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter a number (1-127):\n");
        byte k = sc.nextByte();
        if (k < 0) {
            System.out.println("Invalid number");
        } else {
            byte[][] board = new byte[k + 2][k + 2];
            for (int i = 0; i < k + 2; i++) {
                for (int j = 0; j < k + 2; j++) {
                    if (i == 0 || j == 0 || i == k + 1 || j == k + 1) {
                        board[i][j] = '1';
                    } else {
                        board[i][j] = '0';
                    }
                }
            }
            //now we print:
            for (int i = 0; i < k + 2; i++) {
                for (int j = 0; j < k + 2; j++) {
                    if (board[i][j] == '1') {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }

    }
}
