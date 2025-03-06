import java.util.Scanner;

public class E6_1_a_e {
    public static void main(String[] args) {
        //a
        int sum = 2;
        for (int i = 4; i < 102; i += 2)
            sum += i;
        System.out.println("Part a: " + sum);
        //e
        System.out.println("Enter a  number: ");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int oddSum = 0, remaining;
        while (a != 0){
            remaining = a % 10;
            if (remaining % 2 != 0)
            {
                oddSum += remaining;
            }
            a /= 10;
        }
        System.out.println("Part e: " + oddSum);
    }
}
