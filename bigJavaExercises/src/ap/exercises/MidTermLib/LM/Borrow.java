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
        this.student = student;
        this.book = book;
        this.giverOperator = giverOperator;
        this.borrowDate = LocalDate.now();
    }

    public void returnBook(Operator operator) {
        this.realReturnDate = LocalDate.now();
        this.receiverOperator = operator;
    }
}
