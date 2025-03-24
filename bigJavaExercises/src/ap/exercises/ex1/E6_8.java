package ap.exercises.ex1;

import java.util.Scanner;

public class E6_8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String str = sc.nextLine();
        for ( int i = 0 ; i < str.length() ; i++){
            System.out.println(str.charAt(i));
        }
        sc.close();
    }
}
