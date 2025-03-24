package ap.exercises.ex1;

import java.util.Scanner;
public class E6_3 {
    public static void main(String[] args) {
        String entry = "", upperCaseS = "", underscoreS, everySecondS = "", posOfVowelS = "";
        int numOfVowels = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an string: ");
        entry = sc.nextLine();
        underscoreS = entry;
        char a;
        for (int i = 0; i < entry.length(); i++) {
           if (IsUpperCase(entry.charAt(i))) //a
               upperCaseS +=  entry.charAt(i) + " " ;
           if (i % 2 != 0)              //b
               everySecondS +=  entry.charAt(i);
           if (IsVowel(entry.charAt(i))) { //c & d & e
               underscoreS = underscoreS.substring(0, i) + "_" + underscoreS.substring(i + 1);
               numOfVowels++;
               posOfVowelS += i+1 + " ";
           }

        }
        System.out.println("\nThe uppercase letters are: " + upperCaseS +
                "\nEvery second letter is: " + everySecondS +
                "\nVowels replaced by underscore: " + underscoreS +
                "\nThe number of vowels: " + numOfVowels +
                "\nThe position of vowels: " + posOfVowelS);
sc.close();
    }
    static boolean IsVowel(char c) {
        return (c == 'a' || c == 'e' || c == 'o' || c == 'u' || c == 'i' || c == 'A' || c == 'E' || c == 'O' || c == 'U' || c == 'I');
    }
    static boolean IsUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }
}





/*PERSONAL NOTES:

THIS:
    if (c >= 'A' && c <= 'Z')
            return true;
        else return false;
CAN BE SIMPLIFIED TO:
        return c >= 'A' && c <= 'Z';

 */
