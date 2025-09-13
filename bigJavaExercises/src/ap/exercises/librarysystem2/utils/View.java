package ap.exercises.librarysystem2.utils;

import ap.exercises.librarysystem2.Menu;
import ap.exercises.librarysystem2.model.Book;
import ap.exercises.librarysystem2.model.Student;

import static java.lang.System.*;

public class View {


    InputReader inputReader = new InputReader();
    public Menu menu = new Menu();


    public void printLine(String message) {
        out.println(message);
    }

    public String readBookTitle() {
        return inputReader.readString();
    }

    public int askForID(int idLength) {
        printLine("Enter ID:");
        return inputReader.readIntByLimit(idLength);
    }

    public int askForPW(int pwLength) {
        printLine("Enter PW:");
        return inputReader.readIntByLimit(pwLength);
    }

    public int readIntByLimit(int limit) {
        return inputReader.readIntByLimit(limit);
    }

    public int readInt(int lowerBound, int upperBound) {
        return inputReader.readInt(lowerBound, upperBound);
    }

    public String readString() {
        return inputReader.readString();
    }

    public String askForBookTitle() {
        printLine("Enter book title: ");
        return inputReader.readString();
    }

    public String askForBookAuthor() {
        printLine("Enter book author: ");
        return inputReader.readString();
    }

    public Student getStudentFullInfo(int idLength, int pwLength ) {
        printLine("Enter student name: ");
        String name = readString();
        printLine("Enter student last name: ");
        String lastName = readString();
        printLine("Enter student major: ");
        String major = readString();
        int password = askForPW(idLength);
        int ID = askForID(pwLength);

        return new Student(name, lastName, ID, major, password);
    }

    public Book getBookFullInfo() {
        String title = askForBookTitle();
        String author = askForBookAuthor();
        printLine("Enter publication year: ");
        int publicationYear = inputReader.readInt(0, 2025);
        printLine("Enter book pages: ");
        int pages = inputReader.readInt(0, 999);
        printLine("Enter the number of copies: ");
        int copies = inputReader.readInt(0, 10);

        return new Book(title, author, pages, publicationYear, copies);
    }
}
