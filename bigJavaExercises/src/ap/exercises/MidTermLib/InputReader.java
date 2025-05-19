package ap.exercises.MidTermLib;

import java.util.Scanner;

public class InputReader {
    Scanner sc = new Scanner(System.in);

    public int readInt(int min, int max) {
        int input = Integer.MIN_VALUE;
        do {
            input = sc.nextInt();
            if (input < min || input > max) {
                System.out.println("Please enter a number between " + min + " and " + max);
            }
        }while (input < min || input > max);
        return input;
    }

    public int readPassword(){
        int input;
        do{
            input = sc.nextInt();
            if (input < 1000 || input > 9999) {
                System.out.println("Please enter a number between 1000 and 9999");
            }
        } while (input < 1000 || input > 9999);
        return input;
    }

    public int readID() {
        int input;
        do {
            input = sc.nextInt();
            if (input < 10000 || input > 99999) {
                System.out.println("Please enter a number between 10000 and 99999");
            }
        } while (input < 10000 || input > 99999);
        return input;
    }

    public String readString() {
        String input = "";
        while (input == "") {
            try {
                input = sc.nextLine();
            } catch (Exception e) {
                System.out.println("Try again");
            }
        }
        return input;
    }

}
