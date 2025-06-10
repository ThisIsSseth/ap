package ap.exercises.librarysystem2.model;

import ap.exercises.MidTermLib.model.Book;
import ap.exercises.MidTermLib.model.Operator;
import ap.exercises.MidTermLib.model.Student;

import java.time.LocalDate;

public class Borrow {
    private Student student;
    private Book book;
    private Operator giverOperator;
    private Operator receiverOperator;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate realReturnDate;
    private boolean returnPending = false;


    public Borrow(Student student, Book book) {
        this.student = student;
        this.book = book;
    }

    public void returnBook(Operator operator) {
        this.realReturnDate = LocalDate.now();
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

    /**
     * Borrow or Return confirm*/
    public void confirmByOperator(Operator operator) {
        if(giverOperator == null) {
            this.giverOperator = operator;
            this.borrowDate = LocalDate.now();
            this.returnDate = borrowDate.plusDays(10);
        } else if (receiverOperator == null) {
            this.receiverOperator = operator;
            this.realReturnDate = LocalDate.now();
        }
    }

    public void returnByStudentRequest(){
        returnPending = true;
    }

    public void recreationOfBorrow(Operator giverOperator, LocalDate borrowDate) {
        this.giverOperator = giverOperator;
        this.borrowDate = borrowDate;
    }

    public void recreationOfBorrow(Operator giverOperator, LocalDate borrowDate, Operator receiverOperator, LocalDate realReturnDate) {
        recreationOfBorrow(giverOperator, borrowDate);
        this.receiverOperator = receiverOperator;
        this.realReturnDate = realReturnDate;
    }

    public Student getStudent() {
        return student;
    }

    /**
     * Order: "book title@student id@giverOP id@borrowDate@receiverOp id@returnDate" */
    @Override
    public String toString() {
        String returnString = this.book.getTitle() + "@" + this.student.getId() ;
        if(borrowDate !=null){
            returnString += "@" + this.giverOperator.getId() +"@" + this.borrowDate;
        }
        if (realReturnDate != null) {
            returnString += "@" + this.receiverOperator.getId() +"@" + this.realReturnDate;
        }
        return returnString;
    }
}