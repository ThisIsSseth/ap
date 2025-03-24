package ap.exercises.ex1;

import java.util.Scanner;

public class E6_17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an integer: ");
        int num = sc.nextInt();
        for (int i = 0 ; i < num ; i++) {
            for(int j = 0 ; j < num ; j++)
                System.out.print("*");
            System.out.print(" ");
            if (i == 0 || i == num-1){
                for(int j = 0 ; j < num ; j++)
                    System.out.print("*");
            }
            else {
                System.out.print("*");
                for (int j = 1 ; j < num - 1 ; j++)
                    System.out.print(" ");
                System.out.print("*");
            }
            System.out.println();
        }
        sc.close();
    }
}
