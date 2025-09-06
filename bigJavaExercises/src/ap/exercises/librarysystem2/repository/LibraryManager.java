package ap.exercises.librarysystem2.repository;

import ap.exercises.librarysystem2.model.Book;
import ap.exercises.librarysystem2.model.Library;
import ap.exercises.librarysystem2.model.Operator;
import ap.exercises.librarysystem2.model.Student;
import ap.exercises.librarysystem2.storage.FileInitializer;
import ap.exercises.librarysystem2.storage.JsonHandler;

import java.io.File;
import java.util.List;

public class LibraryManager {
    Library library;

    BookCollection bookCollection;
    StudentCollection studentCollection;
    BorrowCollection borrowCollection;

    File file = new File(FileInitializer.getFileName());



    public LibraryManager(Library library) {
        this.library = library;
        this.bookCollection = new BookCollection(library);
        this.studentCollection = new StudentCollection(library);
        this.borrowCollection = new BorrowCollection(library);
    }

    public Book findBookByTitle(String title) {
        return bookCollection.findBookByTitle(title);
    }


    // ----Student

    public void requestBook(int ID, String title) {
        if(!studentCollection.getStudent(ID).getStatus()) {
            System.out.println("You are not authorized to request a book");
            return;
        }
        if (bookCollection.isAvailable(title)) {
            borrowCollection.requestBook(studentCollection.getStudent(ID), bookCollection.findBookByTitle(title));
            System.out.println("Book requested: " + title);
        }
        else System.out.println("Book not available");
    }

    public List<Book> searchByTitle(String term) {
        if (term == null || term.isEmpty()) return null;
        return bookCollection.searchByTitleContains(term);
    }

    public List<Book> searchByAuthor(String term) {
        if (term == null || term.isEmpty()) return null;
        return bookCollection.searchByAuthorContains(term);
    }

    public List<Book> searchByPublicationYear(int year) {
        return bookCollection.findBooksByPublicationYear(year);
    }

    //----OP
    public void approveBorrowRequest(Operator operator, int ID, String title) {
        if(bookCollection.isAvailable(title) && studentCollection.getStudent(ID) != null) {
            borrowCollection.approveBorrowRequest(operator, studentCollection.getStudent(ID), bookCollection.findBookByTitle(title));
        }
    }

    public void activateStudent(int ID){
        Student student = studentCollection.getStudent(ID);
        if( student != null ) {
            studentCollection.getStudent(ID).setStatus(true);
            System.out.println("Done!");
        }
        else System.out.println("Student not found");
    }
    public void deactivateStudent(int ID){
        Student student = studentCollection.getStudent(ID);
        if( student != null )
        studentCollection.getStudent(ID).setStatus(false);
        else System.out.println("Student not found");
    }

    public void changePW(int newPW, Operator operator){
        operator.setPw(newPW);
    }

    public void addNewBook(Book book){
        bookCollection.addBook(book);
    }


    public void receiveReturnBook(int id, String title, Operator operator) {
        borrowCollection.receiveReturnedBook(borrowCollection.findBorrowByStudentAndBook(studentCollection.getStudent(id),bookCollection.findBookByTitle(title)), operator);
    }

    public String composeOpStat(int id) {
        Student s = studentCollection.getStudent(id);
        if (s == null) return "Student not found.";

        long total = borrowCollection.historyForStudent(s).size();
        long overdue = borrowCollection.overdueCountForStudent(s);
        long notReturned = borrowCollection.notReturnedCountForStudent(s);

        return "Stats for student " + s.getFirstName() + " " + s.getLastName() + " (ID=" + id + "):\n" +
                "- Total borrows: " + total + "\n" +
                "- Currently not returned: " + notReturned + "\n" +
                "- Overdue returns: " + overdue;
    }


    public void save(){
        JsonHandler<Library> jsonHandler = new JsonHandler<>();
        jsonHandler.save(library, file, Library.class);
    }

    public int getTotalUsers() {
        return studentCollection.getSize();
    }
}
