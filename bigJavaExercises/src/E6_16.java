public class E6_16 {
    public static void main(String[] args) {
        for (int y = 1 ; y < 11 ; y++ ) {
            for (int x = 1; x < 11; x++)
                System.out.printf("%4d", y*x);
            System.out.println();
        }
    }
}
