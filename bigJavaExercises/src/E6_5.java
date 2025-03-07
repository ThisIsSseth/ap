import java.util.Scanner;

public class E6_5 {
    public static void main(String[] args) {
        System.out.println("Enter a set of double numbers:\n(enter -1000 to stop the set)\n");

        Scanner sc = new Scanner(System.in);
        DataSet set = new DataSet();
        double temp = sc.nextDouble();
        while (temp != -1000){
            set.add(temp);
            temp = sc.nextDouble();
        }
        System.out.printf("The average: %.2f ", set.getAverage() );
        System.out.println("\nThe smallest: " + set.getSmallest() +
                "\nThe largest: " + set.getLargest() +
                "\nThe range: " + set.getRange());
        sc.close();
    }
    public static class DataSet{
        double smallest = Double.MAX_VALUE;
        double largest = Double.MIN_VALUE;
        double sum = 0;
        byte count = 0;

        public void add(double a){
            sum += a;
            count++;
            if(a < smallest){
                smallest = a;
            }
            else if(a > largest){
                largest = a;
            }
        }
        public double getSmallest(){
            return smallest;
        }
        public double getLargest(){
            return largest;
        }
        public double getAverage(){
            return sum / count;
        }
        public double getRange(){
            return largest - smallest;
        }
    }
}

