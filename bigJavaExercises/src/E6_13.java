import java.util.Scanner;

public class E6_13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an integer: ");
        int num = sc.nextInt();
        for ( int j = 0 ;  num > 0 ; j++){
            System.out.println(num%2);
            num /= 2;
        }
        sc.close();
    }
}
