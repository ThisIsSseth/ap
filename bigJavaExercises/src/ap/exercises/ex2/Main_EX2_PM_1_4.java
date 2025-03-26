package ap.exercises.ex2;

import java.util.Scanner;

public class Main_EX2_PM_1_4 {
    public static void main(String[] args) {
        char entryChar = 'a';
        Scanner sc = new Scanner(System.in);
        while (entryChar != 'q') {
            System.out.println("enter a character:");
            entryChar = sc.next().charAt(0);
            switch (entryChar) {
                case 'w':
                    System.out.println("UP");
                    break;
                case 'a':
                    System.out.println("LEFT");
                    break;
                case 's':
                    System.out.println("DOWN");
                    break;
                case 'd':
                    System.out.println("RIGHT");
                    break;
                case 'q':
                    System.out.println("EXIT");
                    break;
                default:
                    System.out.println("WARNING");

            }
        }
        sc.close();
    }
}
