package ap.exercises;

import ap.exercises.MidTermLib.LM.Library;

import java.util.ArrayList;
import java.util.List;

public class PenAndBookStore {
    public static void main(String[] args) {
        List<Pen> penList = new ArrayList<>();
        List<Book> bookList = new ArrayList<>();

        penList.add(new Pen("Blue", 20, "A"));
        penList.add(new Pen("Red", 30, "b"));

        bookList.add(new Book("A", 100));
        bookList.add(new Book("B", 200));

        for (Pen p : penList) {
            System.out.println(p.toString());
        }
        for (Book b : bookList) {
            System.out.println(b.toString());
        }

        bookList.get(0).setSale(20);
        System.out.println(bookList.get(0).getPrice());

    }
}

class Pen extends Product {
    private String color;
    private String brand;

    public Pen(String color, int price, String brand) {
        super(price);
        this.color = color;
        this.price = price;
        this.brand = brand;
    }

    public String toString() {
        return ("{Color: " + color + ", Price: " + price + ", Brand: " + brand + "}");
    }
}

class Book extends Product {
    private String name;
    private int sale = 0;


    public Book(String name, int price) {
        super(price);
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return ("{Name: " + name + ", Price: " + getPrice() + "}");
    }


}

class Product {
    protected int price;
    protected int sale = 0;

    Product(int price) {
        this.price = price;
    }

    public float getPrice() {
        return price - price * sale / 100;
    }

    public void setSale(int sell) {
        this.sale = sell;
    }
}
