package ap.exercises.librarysystem2.repository;

import ap.exercises.librarysystem2.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class BorrowService {

    private Library library;
    private List<Borrow> borrowList;
    private List<Book> bookList;

    BorrowService(Library library) {
        this.library = library;
        this.borrowList = library.getBorrowList();
        this.bookList = library.getBookList();
    }


    public void requestBook(Student student, Book book) {
        if (!isPending(student, book)) {
            borrowList.add(new Borrow(student, book));
        }
    }

    //We check for availability of the book in libraryManager.
    private void approveBorrowRequest(Borrow borrow, Operator operator) {
        if (borrow == null) return;
        borrow.approveBorrowRequest(operator);
    }

    public void approveBorrowRequest(Operator operator, Student student, Book book) {
        Borrow borrow = findBorrowByStudentAndBook(student, book);
        if (borrow == null) return;
        approveBorrowRequest(borrow, operator);
    }

    public void receiveReturnedBook(Borrow borrow, Operator operator) {
        if (borrow == null) return;
        borrow.returnBook(operator);
    }

    /**
     * Checks to see if there is a GiverOp
     */
    boolean isPending(Student student, Book book) {
        Borrow b = findBorrowByBook(book);
        if (b != null && b.getStudent().equals(student)) {
            if (b.getGiverOperator() != null) {
                return true;
            }
        }
        return false;
    }

    Borrow findBorrowByStudent(Student student) {
        if (student == null) return null;
        for (Borrow borrow : borrowList) {
            if (borrow.getStudent().equals(student)) {
                return borrow;
            }
        }
        return null;
    }

    Borrow findBorrowByBook(Book book) {
        if (book == null) return null;
        for (Borrow borrow : borrowList) {
            if (borrow.getBook().equals(book)) {
                return borrow;
            }
        }
        return null;
    }

    Borrow findBorrowByStudentAndBook(Student student, Book book) {
        for (Borrow borrow : borrowList) {
            if (borrow.getStudent().getId() == student.getId() &&
                    borrow.getBook().getTitle().equals(book.getTitle())) {
                return borrow;
            }
        }
        return null;
    }

    /* simple stats: student's borrow history */
    public List<Borrow> historyForStudent(Student student) {
        return borrowList.stream()
                .filter(b -> b.getStudent().getId() == student.getId())
                .toList();
    }

    /** count total borrows */
    public long totalBorrows() {
        return borrowList.size();
    }

    /** number of currently borrowed books (giverOperator set but realReturnDate null) */
    public long currentlyBorrowedCount() {
        return borrowList.stream().filter(b -> b.getGiverOperator() != null && b.getRealReturnDate() == null).count();
    }

    /** number of overdue returns for a student (realReturnDate after returnDate or realReturnDate null and returnDate before today) */
    public long overdueCountForStudent(Student student) {
        LocalDate today = LocalDate.now();
        return historyForStudent(student).stream().filter(b -> {
            LocalDate rd = b.getReturnDate();
            LocalDate real = b.getRealReturnDate();
            if (real != null) {
                return rd != null && real.isAfter(rd);
            } else {
                return rd != null && rd.isBefore(today);
            }
        }).count();
    }

    /** number of not returned (still borrowed) for a student */
    public long notReturnedCountForStudent(Student student) {
        return historyForStudent(student).stream().filter(b -> b.getRealReturnDate() == null && b.getGiverOperator() != null).count();
    }

    /** average days between borrowDate and realReturnDate across all completed borrows */
    public double averageBorrowDays() {
        List<Borrow> completed = borrowList.stream().filter(b -> b.getBorrowDate() != null && b.getRealReturnDate() != null).toList();
        if (completed.isEmpty()) return 0.0;
        double sum = 0;
        for (Borrow b : completed) {
            sum += java.time.temporal.ChronoUnit.DAYS.between(b.getBorrowDate(), b.getRealReturnDate());
        }
        return sum / completed.size();
    }

    public List<Borrow> getAllBorrows() { return new ArrayList<>(borrowList); }
}




//for now we assume that each student can borrow each book once at a time