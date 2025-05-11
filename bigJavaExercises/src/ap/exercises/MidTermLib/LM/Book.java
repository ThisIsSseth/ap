package ap.exercises.MidTermLib.LM;

public class Book {

    private String title;
    private String author;
    private int pages;
    private int borrower = 0;

    private int publicationYear;

    public int getBorrower() {
        return borrower;
    }

    public void setBorrower(int borrower) {
        this.borrower = borrower;
    }

    public Book(String title, String author, int pages, int publicationYear) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.publicationYear = publicationYear;
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


    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

}
