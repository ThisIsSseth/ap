package ap.exercises.finalexam;

public class Pair < T extends Product, S extends Product> {
    private final T product;
    private final S product2;

    public Pair(T product, S product2) {
        this.product = product;
        this.product2 = product2;
    }
    public T getProduct() {
        return product;
    }
    public S getProduct2() {
        return product2;
    }
}
