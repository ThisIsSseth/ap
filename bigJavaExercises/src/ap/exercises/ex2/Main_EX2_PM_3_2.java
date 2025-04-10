package ap.exercises.ex2;

import javax.swing.*;
import java.util.Scanner;

public class Main_EX2_PM_3_2 {
    public static void main(String[] args) {
        System.out.println("Enter your desired time limit (minutes) and score goal: ");
        Scanner sc = new Scanner(System.in);
        int timeLimit = sc.nextInt();
        int scoreGoal = sc.nextInt();
        PacmanGUI frame = new PacmanGUI(timeLimit, scoreGoal);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
