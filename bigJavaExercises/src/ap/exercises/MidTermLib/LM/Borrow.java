package ap.exercises.MidTermLib.LM;

import ap.exercises.MidTermLib.LM.Members.Operator;
import ap.exercises.MidTermLib.LM.Members.Student;

import java.time.LocalDate;

public class Borrow {
    private Student student;
    private Book book;
    private Operator giverOperator;
    private Operator receiverOperator;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate realReturnDate;

    public Borrow(Student student, Book book, Operator giverOperator) {
        this(student, book, giverOperator, LocalDate.now());
    }

    public Borrow(Student student, Book book, Operator giverOperator, LocalDate borrowDate) {
        this.student = student;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = borrowDate.plusDays(7);
    }

    public void returnBook(Operator operator) {
        this.realReturnDate = LocalDate.now();
        this.receiverOperator = operator;
    }

    public Book getBook() {
        return book;
    }

    public Operator getGiverOperator() {
        return giverOperator;
    }

    public Student getStudent() {
        return student;
    }

    /**
     * Order: book title @ student id @ giverOP id @ receiverOp id @ borrowDate @ returnDate */
    @Override
    public String toString() {
        return this.book.getTitle() + "@" + this.student.getId() + "@" + this.giverOperator.getId() + "@" + this.receiverOperator.getId() + "@" + this.borrowDate + "@" + this.returnDate ;
    }
}
