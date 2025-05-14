package ap.exercises.MidTermLib.LM.LibManTools;

import ap.exercises.MidTermLib.LM.Book;
import ap.exercises.MidTermLib.LM.Borrow;
import ap.exercises.MidTermLib.LM.Library;
import ap.exercises.MidTermLib.LM.Members.Member;
import ap.exercises.MidTermLib.LM.Members.Operator;
import ap.exercises.MidTermLib.LM.Members.Student;
import ap.exercises.MidTermLib.LM.Saver;

public class SingleLibrarySaver {
    Saver studetnListSaver;
    Saver bookListSaver;
    Saver operatorListSaver;
    Saver borrowerListSaver;
    Library library;

    public SingleLibrarySaver(Library library) {
        this.library = library;
        studetnListSaver = new Saver(this.library.getLibName() + "Students");
        operatorListSaver = new Saver(this.library.getLibName() + "Operators");
        borrowerListSaver = new Saver(this.library.getLibName() + "Borrowers");
        bookListSaver = new Saver(this.library.getLibName() + "Books");
    }

    String studentListFormater() {
        String studentListString = "";
        for (Member a : library.getStudentMap().values()) {
            studentListString += a + "\n";
        }
        return studentListString;
    }

    String bookListFormater() {
        String bookListString = "";
        for (Book a : library.getBookList()) {
            bookListString += a + "\n";
        }
        return bookListString;
    }

    String operatorListFormater() {
        String operatorListString = "";
        for (Operator a : library.getOperatorMap().values()) {
            operatorListString += a + "\n";
        }
        return operatorListString;
    }

    String borrowerListFormater() {
        String borrowerListString = "";
        if(!library.getBorrowList().isEmpty()) {
            for (Borrow b : library.getBorrowList()) {
                borrowerListString += b + "\n";
            }
        }
        return borrowerListString;
    }

    public void saveData() {
        studetnListSaver.writeInto(studentListFormater());
        bookListSaver.writeInto(bookListFormater());
        operatorListSaver.writeInto(operatorListFormater());
        borrowerListSaver.writeInto(borrowerListFormater());
        System.out.println("Saved successfully");
    }

    void studentMapReader() {
            for (String a : studetnListSaver.readLinetoList()) {
                String[] b = a.split("@");
                Student s = new Student(b[0], b[1], Integer.parseInt(b[2]), b[3], Integer.parseInt(b[4]));
                library.getStudentMap().put(s.getId(), s);
            }
    }

    void setOperatorListReader(){
        for (String a : operatorListSaver.readLinetoList()) {
            String[] b = a.split("@");
            Book c = new Book (b[0], b[1], Integer.parseInt(b[2]), Integer.parseInt(b[3]), Integer.parseInt(b[4]));
            library.getBookList().add(c);
        }
    }
}


