package ap.exercises.librarysystem2.repository;

import ap.exercises.librarysystem2.model.Book;
import ap.exercises.librarysystem2.model.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

class BookCollection {

    private Library library;
    private List<Book> books;

    BookCollection(Library library) {
        this.library = library;
        this.books = library.getBookList();
    }

    public Book findBookByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {return null;}
        String t = title.trim().toLowerCase(Locale.ROOT);
        for (Book book : books) {
            if (book.getTitle().equals(t)) {
                return book;
            }
        }
        return null;
    }


    public List<Book> searchByTitleContains(String term) {
        if (term == null || term.isEmpty()) return new ArrayList<>();
        String t = term.toLowerCase(Locale.ROOT);
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase(Locale.ROOT).contains(t))
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthorContains(String term) {
        if (term == null || term.isEmpty()) return new ArrayList<>();
        String t = term.toLowerCase(Locale.ROOT);
        return books.stream()
                .filter(b -> b.getAuthor().toLowerCase(Locale.ROOT).contains(t))
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByPublicationYear(int publicationYear) {
        return books.stream()
                .filter(b -> b.getPublicationYear() == publicationYear)
                .collect(Collectors.toList());
    }



    public boolean isAvailable(String title) {
        if (findBookByTitle(title) != null) {
            return findBookByTitle(title).getCopies() > 0;
        }
        return false;
    }

    public void addBook(Book book) {
        if (findBookByTitle(book.getTitle()) == null) {
            books.add(book);
        }
    }

}
