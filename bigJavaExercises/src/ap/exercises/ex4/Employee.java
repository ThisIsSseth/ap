package ap.exercises.ex4;

public class Employee {
    private final String name;
    private double salary;

    public Employee(String name, int salary) {
        this.salary = salary;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double byPercent){
        salary += byPercent * salary /100;
    }
}
