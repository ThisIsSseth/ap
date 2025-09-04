package ap.exercises.librarysystem2.repository;

import ap.exercises.librarysystem2.model.Book;
import ap.exercises.librarysystem2.model.Library;

import java.util.List;

class BookCollection {

    private Library library;
    private List<Book> books;

    BookCollection(Library library) {
        this.library = library;
        this.books = library.getBookList();
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public Book findBookByAuthor(String author) {
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                return book;
            }
        }
        return null;
    }

    public Book fundBookByPublicationYear(int publicationYear) {
        for (Book book : books) {
            if (book.getPublicationYear() == publicationYear) {
                return book;
            }
        }
        return null;
    }


    public boolean isAvailable(String title) {
        if (findBookByTitle(title) != null) {
            if (findBookByTitle(title).getCopies() > 0) {
                return true;
            }
        }
        return false;
    }

}
