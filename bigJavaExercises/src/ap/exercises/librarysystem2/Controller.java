package ap.exercises.librarysystem2;

import ap.exercises.librarysystem2.model.*;
import ap.exercises.librarysystem2.repository.LibraryManager;
import ap.exercises.librarysystem2.utils.LoginStatus;
import ap.exercises.librarysystem2.utils.Session;
import ap.exercises.librarysystem2.utils.View;

import java.util.List;

import static java.lang.System.*;


public class Controller {
    Library library;
    AuthenticationService authService;
    LibraryManager libraryManager;

    final int ID_LENGTH = Member.ID_LENGTH;
    final int PW_LENGTH = Member.PW_LENGTH;

    //    InputReader inputReader = new InputReader();
    View v = new View();

    Session session = new Session();


    public Controller(Library library) {
        this.library = library;
        libraryManager = new LibraryManager(library);
        this.authService = new AuthenticationService(library);
    }

    public LoginStatus getLoginStatus() {
        return session.getLoginStatus();
    }


    //_____Menus
    // prbbly jst keep the menu directors

    public void studentMenuDirector(int option) {
        switch (option) {
            case 0 -> logout();
            case 1 -> searchBook();
            case 2 -> requestBook();
        }
    }

    public void guestMenuDirector(Integer option) {
        switch (option) {
//            case 1 -> out.println("Total number of users: " + libraryManager.getTotalUsers());
            case 2 -> searchBook();
            case 0 -> logout();
        }
        libraryManager.save();
    }

    public void operatorMenuDirector(Integer option) {
        switch (option) {
            case 0 -> logout();
            case 1 -> addBook();
            case 2 -> searchBook();
            case 3 -> editBook();
            case 4 -> acceptBorrowRequest();
            case 5 -> receiveReturnedBook();
            case 6 -> libraryManager.activateStudent(v.askForID(ID_LENGTH));
            case 7 -> libraryManager.deactivateStudent(v.askForID(ID_LENGTH));
            case 8 -> studentStats();
            case 10 -> {
                v.printLine("Enter new password: ");
                libraryManager.changePW(v.readIntByLimit(PW_LENGTH), session.getOperator());
            }
        }

        libraryManager.save();
    }

    public void managerMenuDirector(Integer option) {

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
        v.printLine("Please enter the name of the book you want to edit: ");
        String title = v.readBookTitle();
        Book book = libraryManager.findBookByTitle(title);
        if (book != null) {
            Menu::s
            int option = inputReader.readInt(1, 5);
            System.out.println("Enter new data: ");
            switch (option) {
                case 1 -> book.setTitle(v.askForBookTitle());
                case 2 -> book.setAuthor(inputReader.readString());
                case 3 -> book.setPages(inputReader.readInt(1, 5000));
                case 4 -> book.setPublicationYear(inputReader.readInt(0, 3000));
                case 5 -> book.setCopies(inputReader.readInt(0, 50));
            }
        } else {
            v.printLine("Book not found!");
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

    private void studentStats() {
        System.out.println(libraryManager.composeOpStat(v.askForID(ID_LENGTH)));
    }


    private void searchBook() { //it has a list and a director NEEDS EDITING
        //show search book list
        System.out.println("""
                Search by:
                1. Title
                2. Author
                3. Publication Year
                0. Go Back
                """);
        int option = inputReader.readInt(0, 3);
        List<Book> results = null;
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
            default -> {
                return;
            }
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
        libraryManager.addNewBook(v.getBookFullInfo());
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
        out.println("Enter ID: ");
        int ID = inputReader.readIntByLimit(ID_LENGTH);
        out.println("Enter password: ");
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
        session.setLoginStatus(LoginStatus.LOGGED_OUT);
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


}

