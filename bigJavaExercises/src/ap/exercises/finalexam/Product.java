package ap.exercises.finalexam;

public class Product implements Comparable<Product> {
    @Override
    public int compareTo(Product o) {
        return this.getPrice().compareTo(o.getPrice());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product))
            return false;
        Product other = (Product) o;
        boolean priceEquals = (this.price == null && other.price == null)
                || (this.price != null && this.price.equals(other.getPrice()));
        boolean nameEquals = (this.name == null && other.getName() == null)
                || (this.name != null && this.name.equals(other.getName()));
        return priceEquals && nameEquals;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(price, name);
    }

    private Integer price;
    private String name;

    public Product(int price, String name) {
        this.price = price;
        this.name = name;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name=" + name + " | price=" + price ;
    }
}
