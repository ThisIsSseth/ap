package ap.exercises.librarysystem2.model;

import java.time.LocalDate;

public class Borrow {
    private Student student;
    private Book book;

    private Operator giverOperator = null;
    private Operator receiverOperator = null;

    private LocalDate borrowDate;
    private int STDBorrowTimeInterval = 5;
    private LocalDate returnDate;
    private LocalDate realReturnDate;

    public Borrow(Student student, Book book) {
        this.student = student;
        this.book = book;
    }


    public void approveBorrowRequest(Operator operator) {
        this.giverOperator = operator;
        book.substituteCopyByOne();
        this.borrowDate = LocalDate.now();
        this.returnDate = borrowDate.plusDays(STDBorrowTimeInterval);
    }

    public void returnBook(Operator operator) {
        this.realReturnDate = LocalDate.now();
        book.addCopyByOne();
        this.receiverOperator = operator;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getRealReturnDate() {
        return realReturnDate;
    }

    public Operator getGiverOperator() {
        return giverOperator;
    }

    public Operator getReceiverOperator() {
        return receiverOperator;
    }

    public Student getStudent() {
        return student;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}


//    /**
//     * Borrow or Return confirm*/
//    public void confirmByOperator(Operator operator) {
//        if(giverOperator == null) {
//            this.giverOperator = operator;
//            this.borrowDate = LocalDate.now();
//            this.returnDate = borrowDate.plusDays(10);
//        } else if (receiverOperator == null) {
//            this.receiverOperator = operator;
//            this.realReturnDate = LocalDate.now();
//        }
//    }