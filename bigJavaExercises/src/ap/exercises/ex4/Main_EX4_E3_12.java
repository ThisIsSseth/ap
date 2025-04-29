package ap.exercises.ex4;

/**
 Employee tester
 */
public class Main_EX4_E3_12 {
    public static void main(String[] args) {
        Employee bob = new Employee("Bob", 20000);
        System.out.println(bob.getName());
        System.out.println("Expected: Bob");
        System.out.println(bob.getSalary());
        System.out.println("Expected: 20000.0");
        bob.raiseSalary(10);
        System.out.println(bob.getSalary());
        System.out.println("Expected: 22000.0");
    }

}
