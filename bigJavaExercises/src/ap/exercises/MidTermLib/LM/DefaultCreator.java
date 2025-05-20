package ap.exercises.MidTermLib.LM;

import ap.exercises.MidTermLib.LM.Members.Manager;
import ap.exercises.MidTermLib.LM.Members.Student;

public class DefaultCreator {

    Library library = new Library("default Library");
    Manager manager  = new Manager("Michael", "Fred", 5000, "Library Management PhD");

    Student student01 = new Student("Student01", "Last Name01", 10001, "English", 1000);
    Student student02 = new Student("Student02", "Last Name02", 10002, "Art", 1002);
    Student student03 = new Student("Student03", "Last Name03", 10003, "Sports", 1003);

    Book book01 = new Book("Book01", "Author01", 100, 1990);
    Book book02 = new Book("Book02", "Author02", 101, 1991);
    Book book03 = new Book("Book03", "Author03", 10, 1992);



    public Library defaultLibrary() {
        setDefault();
        return library;
    }

    private void setDefault() {
        library.getStudentMap().put(student01.getId(), student01);
        library.getStudentMap().put(student02.getId(), student02);
        library.getStudentMap().put(student03.getId(), student03);
        library.getBookList().add(book01);
        library.getBookList().add(book02);
        library.getBookList().add(book03);
        library.setManager(manager);
    }
}