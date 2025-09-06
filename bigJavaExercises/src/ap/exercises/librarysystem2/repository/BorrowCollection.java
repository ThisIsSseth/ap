package ap.exercises.librarysystem2.repository;

import ap.exercises.librarysystem2.model.Book;
import ap.exercises.librarysystem2.model.Borrow;
import ap.exercises.librarysystem2.model.Library;
import ap.exercises.librarysystem2.model.Student;

import java.util.List;

class BorrowCollection {

    private Library library;
    private List<Borrow> borrowList;
    private List<Book> bookList;

    BorrowCollection(Library library) {
        this.library = library;
        this.borrowList = library.getBorrowList();
        this.bookList = library.getBookList();
    }


    public void requestBook(Student student, Book book) {
        if(!isPending(student, book)) {
            borrowList.add(new Borrow(student, book));
        }
//        for (Borrow borrow : borrowList) {
//            if(borrow.getStudent().getId() == id && borrow.getBook().getTitle().equals(title)){
//                if (borrow.getGiverOperator() == null){
//                    System.out.println("Request pending");
//                }
//                System.out.println("You have already borrowed the book");
//            }
//            if()
//        }
    }

    boolean isPending(Student student, Book book) {
        Borrow b = findBorrowByBook(book);
        if (b != null && b.getStudent().equals(student)) {
            if (b.getGiverOperator()!=null) {
                return true;
            }
        }
        return false;
    }

    Borrow findBorrowByStudent(Student student) {
        for (Borrow borrow : borrowList) {
            if (borrow.getStudent().equals(student)) {
                return borrow;
            }
        }
        return null;
    }

    Borrow findBorrowByBook(Book book) {
        for (Borrow borrow : borrowList) {
            if (borrow.getBook().equals(book)) {
                return borrow;
            }
        }
        return null;
    }


}
