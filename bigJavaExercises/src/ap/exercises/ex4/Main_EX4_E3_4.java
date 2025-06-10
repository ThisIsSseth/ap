package ap.exercises.ex4;

import java.util.Scanner;

public class Main_EX4_E3_4 {
    public static void main(String[] args) {
        Circuit circuit = new Circuit();
        Scanner sc = new Scanner(System.in);
        byte input = 0;
        while (true){
            System.out.println("""
                    1. toggle switch1
                    2. toggle switch2
                    0. Exit
                    """);
            input = sc.nextByte();
            switch(input){
                case 1-> circuit.toggleFirstSwitch();
                case 2-> circuit.toggleSecondSwitch();
                case 0 -> System.exit(0);
            }
            System.out.println("Switch 1: " + (circuit.getFirstSwitchState() == 0 ? "Down" : "Up") + ", Switch 2: " + (circuit.getSecondSwitchState() == 0 ? "Down" : "Up") + "\nLamp state: " + (circuit.getLampState() == 0 ? "Off" : "On"));
        }

    }
}
