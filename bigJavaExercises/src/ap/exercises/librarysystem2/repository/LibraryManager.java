package ap.exercises.librarysystem2.repository;

import ap.exercises.librarysystem2.model.Library;
import ap.exercises.librarysystem2.model.Operator;
import ap.exercises.librarysystem2.storage.FileInitializer;
import ap.exercises.librarysystem2.storage.JsonHandler;

import java.io.File;

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


    // ----Student

    public void requestBook(int ID, String title) {
        if (bookCollection.isAvailable(title)) {
            borrowCollection.requestBook(studentCollection.getStudent(ID), bookCollection.findBookByTitle(title));
        }
        else System.out.println("Book not found or not available");
    }

    //----OP
    public void changePW(){}

    public void approveBorrowRequest(Operator operator, int ID, String title) {
        if(bookCollection.isAvailable(title) && studentCollection.getStudent(ID) != null) {
            borrowCollection.approveBorrowRequest(operator, studentCollection.getStudent(ID), bookCollection.findBookByTitle(title));
        }
    }
    


    public void save(){
        JsonHandler<Library> jsonHandler = new JsonHandler<Library>();
        jsonHandler.save(library, file, Library.class);
    }


}
