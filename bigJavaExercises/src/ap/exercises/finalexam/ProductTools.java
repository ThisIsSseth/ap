package ap.exercises.finalexam;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.reducing;

public class ProductTools {

    public static void printByPrice(List<Product> products) {
        List result = products.stream()
                .distinct()
                .sorted()
                .toList();
        result.forEach(System.out::println);
    }

    public static Pair<Pen, Book> maxPrices(List<Product> products) {
        Integer max = Integer.MIN_VALUE;
        Pen pen = null;
        Book book = null;
        for (Product product : products) {
            if (product instanceof Pen && product.getPrice() > max) {
                pen = (Pen) product;
            }
            if (product instanceof Book && product.getPrice() > max) {
                book = (Book) product;
            }
        }
        return new Pair(pen, book);
    }


//    public static HashMap<Integer, Colours> getColoursMap(List<Product> products) {
//        List<Pen> pens = products.stream()
//                .filter(p -> p instanceof Pen)
//                .map(p -> (Pen) p)
//                .collect(Collectors.groupingBy(Pen::getColour, reducing(0, e -> 1)))
//                .collect()
//                .collect(toList());
//    }


    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Pen(10, "A", Colours.Green));
        products.add(new Pen(15, "B", Colours.Red));
        products.add(new Pen(80, "C", Colours.Blue));
        products.add(new Book(80, "B", "T1", "author"));
        products.add(new Book(80, "B", "T1", "author"));
        products.add(new Book(80, "B", "T1", "author"));

//        products.get(4).equals(products.get(5));
//        for (Product p : ProductTools.printByPrice(products))
//            System.out.println(p.toString());
        printByPrice(products);
        System.out.println("--------");
        System.out.println(maxPrices(products).getProduct().toString());
        System.out.println(maxPrices(products).getProduct2().toString());



    }
}
