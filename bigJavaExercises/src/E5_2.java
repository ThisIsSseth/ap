import java.util.Scanner;

public class E5_2 {
    public static void main(String[] args) {
        System.out.print("Enter any number:");
        Scanner in = new Scanner(System.in);
        float number = in.nextFloat();
        if (number == 0) {
            System.out.println("Zero");
        } else if (number < 0) {
            System.out.println("negetive");
        } else {
            System.out.println("positive");
        }
        if (Math.abs(number) < 1) // (number < 1) || (number > -1)
        {
            System.out.println("Small");
        } else if (Math.abs(number) > 1000000) {
            System.out.println("Large");
        }

        in.close();
    }
}
