import java.util.Scanner;
public class E6_2 {
    public static void main(String[] args)
    {
        Scanner in  = new Scanner(System.in);
        int input, smallest, biggest, coo = 0, coe = 0, sum = 0;          //coo: count of odd numbers, coe: count of even numbers
        String cTotal = "", adjDup = "";                                   //cTotal: Cumulative total, adjDup: adjacent duplicates
        System.out.println("Enter a sequence of integers:\nenter -1000 to exit");
        input = in.nextInt();
        if (input == -1000)
            return;
        smallest = input;
        biggest = input;
        int ffInput = -1000,fInput = input;
        while (input != -1000){
            if (input == fInput)
                if ( ffInput != input)
                adjDup += " " + input;

            if (input % 2 != 0)
                coo++;
            else
                coe++;

            if (input > biggest)
                biggest = input;
            else if (input < smallest)
                smallest = input;

            sum += input;
            cTotal += " " + sum;
            ffInput = fInput;
            fInput = input;
            input = in.nextInt();
        }
        System.out.println("Smallest: " + smallest +
                        "\n Biggest: " + biggest +
                        "\nThe number of Evens: " + coe +
                        "\nThe number of Odds: " + coo +
                        "\nCumulative total: " + cTotal +
                        "\nAdjacent duplicates: " + adjDup);
    }
}
