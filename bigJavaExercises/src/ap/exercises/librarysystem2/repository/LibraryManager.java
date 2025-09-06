package ap.exercises.librarysystem2.repository;

import ap.exercises.librarysystem2.model.Library;

public class LibraryManager {
    BookCollection bookRepository;
    StudentCollection studentRepository;
    BorrowCollection borrowRepository;


    public LibraryManager(Library library) {
        this.bookRepository = new BookCollection(library);
        this.studentRepository = new StudentCollection(library);
        this.borrowRepository = new BorrowCollection(library);
    }

    public void requestBook(int ID, String title) {
        if (bookRepository.isAvailable(title)) {
            borrowRepository.requestBook(studentRepository.getStudent(ID), bookRepository.findBookByTitle(title));
        }
        else System.out.println("Book not found or not available");
    }


}
