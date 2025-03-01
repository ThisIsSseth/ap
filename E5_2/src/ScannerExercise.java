package E5_2.src;
import java.util.Scanner;
public class ScannerExercise {
    public static void main(String[] args) {
        System.out.println("Enter your name:\n");
        Scanner in = new Scanner (System.in);
        String name = in.nextLine();
        System.out.println("Hello," + name + "!");
        in.close();
    }
}
