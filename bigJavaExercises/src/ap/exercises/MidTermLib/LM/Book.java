package ap.exercises.MidTermLib.LM;

public class Book {

    private String title;
    private String author;
    private int pages;
    private int publicationYear;
    private int copies = 1;
    private String regex = "@";


    public String getRegex() {
        return regex;
    }

    public Book(String title, String author, int pages, int publicationYear, int copies) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.publicationYear = publicationYear;
        this.copies = copies;
    }

    public Book(String title, String author, int pages,  int publicationYear) {
        this(title, author, pages, publicationYear, 1);
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getPages() {
        return pages;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getCopies() {return copies;}

    /**
     * substitutes one from the copies */
    public void substituteCopyByOne() {
        copies--;
    }

    public void addCopyByOne() {
        copies++;
    }
    @Override
    public String toString() {
        return this.title +regex + this.author +regex + this.pages +regex + this.publicationYear + regex + this.copies + regex;
    }

}
