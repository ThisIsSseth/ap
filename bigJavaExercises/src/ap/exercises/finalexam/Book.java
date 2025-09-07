package ap.exercises.finalexam;

public class Book extends Product{
    private String title;
    private String author;

    public Book(Integer price, String name, String title, String author) {
        super(price, name);
        this.title = title;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return super.toString() + " | title: " + title + " | author: " + author;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Book book = (Book) obj;
        return title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), title, author);
    }

}
