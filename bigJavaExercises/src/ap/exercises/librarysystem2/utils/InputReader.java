package ap.exercises.librarysystem2.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputReader {
    Scanner sc = new Scanner(System.in);

    /**
     * gets an {@code int} between min and max
     */
    public int readInt(int min, int max) {
        int input = Integer.MIN_VALUE;
        try {
            do {
                input = sc.nextInt();
                sc.nextLine();
                if (input < min || input > max) {
                    System.out.println("Please enter a number between " + min + " and " + max);
                }
            } while (input < min || input > max);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            sc.nextLine();
        }
        return input;
    }

    /**
     * @param numberOfDigits the power of minimum limit and 1/10th of the max limit
     */
    public int readIntByLimit(int numberOfDigits) {
        int Min = (int) (Math.pow(10, numberOfDigits - 1));
        int input = 0;
        try {
            do {
                input = sc.nextInt();
                sc.nextLine();
                if (input < Min || input >= Min * 10) {
                    System.out.println("Please enter a number between " + Min + " and " + (Min * 10 - 1));
                }

            } while (input < Min || input >= Min * 10);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            sc.nextLine();
        }
        return input;
    }

    /**
     * Asks for a String input until it's not empty
     */
    public String readString() {
        String input = "";
        while (input.trim().isEmpty()) {
            try {
                input = sc.nextLine();
            } catch (Exception e) {
                System.out.println("Try again");
            }
        }
        return input;
    }


}
