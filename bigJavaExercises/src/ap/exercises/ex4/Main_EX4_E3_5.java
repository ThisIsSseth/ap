package ap.exercises.ex4;

public class Main_EX4_E3_5 {
    public static void main(String[] args) {
        HallwayLamp hallwayLamp = new HallwayLamp();
        CircuitTester tester = new CircuitTester(hallwayLamp);
        System.out.println("Testing all possible states...");
        tester.test();
        System.out.println("Done testing all possible states.");

    }
}
