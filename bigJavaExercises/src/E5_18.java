import java.util.Scanner;

public class E5_18 {
    public static void main(String[] args) {
        String s1, s2, s3;
        Scanner in  = new Scanner(System.in);
        System.out.print("Enter three strings: ");
        s1 = in.next();
        s2 = in.next();
        s3 = in.next();
        String tempS1 = s1.toUpperCase(), tempS2 = s2.toUpperCase(), tempS3 = s3.toUpperCase(), max;
        if (tempS1.compareTo(tempS2) < 0)
            max = s1;
        else max = s2;
        if (max.compareToIgnoreCase(tempS3) > 0)
            max = s3;
        System.out.println(max);
        String min;
        if (tempS1.compareTo(tempS3) > 0)
            min = s1;
        else min = s3;
        if (min.compareTo(tempS2) < 0)
            min = s2;
        if (!s1.equals(max) && !s1.equals(min))
            System.out.println(s1);
        else if (!s2.equals(max) && !s2.equals(min))
            System.out.println(s2);
        else if (!s3.equals(max) && !s3.equals(min))
            System.out.println(s3);
        System.out.println(min);




    }
}
