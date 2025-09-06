package ap.exercises.librarysystem2;

import ap.exercises.librarysystem2.model.*;
import ap.exercises.librarysystem2.repository.LibraryManager;
import ap.exercises.librarysystem2.utils.AuthenticationService;
import ap.exercises.librarysystem2.utils.DefaultLibraryCreator;
import ap.exercises.librarysystem2.utils.InputReader;

import java.util.List;

import static java.lang.System.*;


public class Controller {
    Library library;
    AuthenticationService authService;
    LibraryManager libraryManager;
    final int ID_LENGTH = Member.ID_LENGTH;
    final int PW_LENGTH = Member.PW_LENGTH;

    InputReader inputReader = new InputReader();

    public enum LoginStatus {
        LOGGED_OUT,
        GUEST,
        STUDENT,
        OPERATOR,
        MANAGER;
    }

    LoginStatus loginStatus = LoginStatus.LOGGED_OUT;

    Student student = null;
    Manager manager = null;
    Operator operator = null;

    public Controller(Library library) {
        this.library = library;
        libraryManager = new LibraryManager(library);
        this.authService = new AuthenticationService(library);
    }


    //_____Menus

    public void studentMenu(int option) {
        switch (option) {
            case 0 -> logout();
            case 1 -> searchBook();
            case 2 -> requestBook();
        }
    }

    public void guestMenu(Integer option) {
        switch (option) {
            case 1 -> out.println("Total number of users: " + libraryManager.getTotalUsers());
            case 2 -> searchBook();
            case 0 -> logout();
        }
        libraryManager.save();
    }

    public void operatorMenu(Integer option) {
        switch (option) {
            case 0 -> logout();
            case 1 -> addBook();
            case 2 -> searchBook();
            case 3 -> editBook();
            case 4 -> acceptBorrowRequest();
            case 5 -> receiveReturnedBook();
            case 6 -> {
                System.out.println("Enter ID:");
                libraryManager.activateStudent(inputReader.readIntByLimit(ID_LENGTH));
            }
            case 7 -> {
                System.out.println("Enter ID:");
                libraryManager.deactivateStudent(inputReader.readIntByLimit(ID_LENGTH));
            }
            case 8 -> {advancedStats();}

            case 10 -> {System.out.println("Enter new password:");
            libraryManager.changePW(inputReader.readIntByLimit(PW_LENGTH), operator );}
        }
        libraryManager.save();
    }
    public void managerMenu(Integer option) {
    }

    public void startMenuDirector(int option) {
        switch (option) {
            case 1 -> studentSignUp();
            case 2 -> studentLogin();
            case 3 -> operatorLogin();
            case 4 -> managerLogin();
            case 5 -> guestLogin();
            case 6 -> saveAndExit();
        }
        libraryManager.save();
    }

    // -------op

    private void editBook() {
        System.out.println("Enter the title of the book you want to edit: ");
        String title = inputReader.readString();
        Book book = libraryManager.findBookByTitle(title);
        if (book != null) {
            System.out.println("""
                    Choose field to edit:
                    1. Title
                    2. Author
                    3. Pages
                    4. Publication year
                    5. Copies""");
            int option = inputReader.readInt(1, 5);
            System.out.println("Enter new data: ");
            switch (option) {
                case 1 -> book.setTitle(inputReader.readString());
                case 2 -> book.setAuthor(inputReader.readString());
                case 3 -> book.setPages(inputReader.readInt(1, 5000));
                case 4 -> book.setPublicationYear(inputReader.readInt(0, 3000));
                case 5 -> book.setCopies(inputReader.readInt(0, 50));
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    private void receiveReturnedBook() {
        out.println("Enter student ID: ");
        int id = inputReader.readIntByLimit(ID_LENGTH);
        out.println("Enter book title: ");
        String title = inputReader.readString();
        libraryManager.receiveReturnBook(id, title, operator);
    }

    private void acceptBorrowRequest() {
        System.out.println("Enter student ID: ");
        int id = inputReader.readIntByLimit(ID_LENGTH);
        System.out.println("Enter book title: ");
        String title = inputReader.readString();
        libraryManager.approveBorrowRequest(operator, id, title);
    }

    private void advancedStats() {
        System.out.println("Enter student ID: ");
        int id = inputReader.readIntByLimit(ID_LENGTH);
        out.println(libraryManager.composeOpStat(id));
    }


    private void searchBook() {
        System.out.println("""
                Search by:
                1. Title
                2. Author
                3. Publication Year
                0. Go Back
                """);
        int option = inputReader.readInt(0, 3);
        List<Book> results = null;
        inputReader.eater();
        switch (option) {
            case 1 -> {
                System.out.print("Title: ");
                results = libraryManager.searchByTitle(inputReader.readString());
            }
            case 2 -> {
                System.out.print("Author: ");
                results = libraryManager.searchByAuthor(inputReader.readString());
            }
            case 3 -> {
                System.out.print("Publication year: ");
                results = libraryManager.searchByPublicationYear(inputReader.readInt(0, 3000));
            }
            default -> { return; }
        }
        if (results == null || results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            results.forEach(System.out::println);
        }
    }

    private void requestBook() {
        System.out.println("Enter book name: ");
        String title = inputReader.readString();
        libraryManager.requestBook(student.getId(), title);
    }


    private void addBook() {
        libraryManager.addNewBook(getBookFullInfo());
    }



    // ----LOGINs

    private void guestLogin() {
        loginStatus = LoginStatus.GUEST;
    }
    private void operatorLogin() {
        out.println("Enter ID: ");
        int ID = inputReader.readIntByLimit(ID_LENGTH);
        out.println("Enter password: ");
        int password = inputReader.readIntByLimit(PW_LENGTH);
        operator = authService.OPLogin(ID, password);
        if (operator != null) {
            loginStatus = LoginStatus.OPERATOR;
        }
    }

    private void managerLogin() {
        int ID = inputReader.readIntByLimit(ID_LENGTH);
        int password = inputReader.readIntByLimit(PW_LENGTH);
        manager = authService.managerLogin(ID, password);
        if (manager != null) {
            loginStatus = LoginStatus.MANAGER;
        }
    }

    private void studentLogin() {
        System.out.println("Enter ID:");
        int ID = inputReader.readIntByLimit(ID_LENGTH);
        System.out.println("Enter password:");
        int password = inputReader.readIntByLimit(PW_LENGTH);
        student = authService.studentLogin(ID, password);
        if (student != null) {
            loginStatus = LoginStatus.STUDENT;
        }
    }

    private void studentSignUp() {
        authService.studentSignUp(getStudentFullInfo());
    }

    private void logout() {
        System.out.println("You are logged out");
        loginStatus = LoginStatus.LOGGED_OUT;
    }


    //utils________

    private void saveAndExit() {
        libraryManager.save();
        exit(0);
    }

    private Student getStudentFullInfo() {
        System.out.println("Enter student name: ");
        String name = inputReader.readString();
        System.out.println("Enter student last name: ");
        String lastName = inputReader.readString();
        System.out.println("Enter student major: ");
        String major = inputReader.readString();
        System.out.println("Enter student password: ");
        int password = inputReader.readIntByLimit(PW_LENGTH);
        System.out.println("Enter student ID: ");
        int ID = inputReader.readIntByLimit(ID_LENGTH);

        return new Student(name, lastName, ID, major, password);
    }

    private Book getBookFullInfo() {
        System.out.println("Enter book name: ");
        String name = inputReader.readString();
        System.out.println("Enter book author: ");
        String author = inputReader.readString();
        System.out.println("Enter publication year: ");
        int publicationYear = inputReader.readInt(0, 2025);
        System.out.println("Enter book pages: ");
        int pages = inputReader.readInt(0, 999);
        System.out.println("Enter the number of copies: ");
        int copies = inputReader.readInt(0, 10);

        return new Book(name, author, pages, publicationYear, copies);
    }
}

