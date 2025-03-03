import java.util.Scanner;

public class E6_1_a_e {
    public static void main(String[] args) {
        //a
        int sum = 2;
        for (int i = 4; i < 202; i += 2)
            sum += i;
        System.out.println("Part a: " + sum);
        //e
        System.out.println("Enter a  number: ");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int oddSum = 0, temp;
        while (a != 0){
            temp = a % 10;
            if (temp % 2 != 0)
            {
                oddSum += temp;
            }
            a /=
        }

    }
}
