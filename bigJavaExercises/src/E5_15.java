import java.util.Scanner;

public class E5_15 {
    public static void main(String[] args) {
        System.out.print("Enter income:");
        Scanner in = new Scanner(System.in);
        int income = in.nextInt();
        int tax = 0;
        if (income >= 0) {
            tax += income > 50000 ? 500 : income / 100;
            income -= 50000;
            if (income >= 0) {
                tax += income > 25000 ? 500 : income / 50;
                income -= 25000;
                if (income >= 0) {
                    tax += income > 25000 ? 750 : 3 * income / 100;
                    income -= 25000;
                    if (income >= 0) {
                        tax += income > 150000 ? 4 * 1500 : 4 * income / 100;
                        income -= 150000;
                        if (income >= 0) {
                            tax += income > 250000 ? 4 * 2500 : 5 * income / 100;
                            income -= 250000;
                            if (income >= 0) {
                                tax += 6 * income / 100;
                            }
                        }
                    }
                }
            }

        }
        System.out.println("\n" + tax);
        in.close();
    }

}
