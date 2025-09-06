package ap.exercises.librarysystem2.model;

public class Book {

    private String title;

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

    public void setCopies(int copies) {
        this.copies = copies;
    }

    private String author;
    private int pages;
    private int publicationYear;
    private int copies = 1;
//    private String regex = "@";


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

//    public String getRegex() {
//        return regex;
//    }

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

    /**substitutes one from the copies */
    public boolean substituteCopyByOne() {
        if (copies > 0) {
            copies--;
            return true;
        }
        return false;
    }

    /**adds one to the number of copies*/
    public void addCopyByOne() {
        copies++;
    }

    @Override
    public String toString() {
        return String.format("Title: %s | Author: %s | Year: %d | Pages: %d | Copies: %d",
                title, author, publicationYear, pages, copies);
    }


}
