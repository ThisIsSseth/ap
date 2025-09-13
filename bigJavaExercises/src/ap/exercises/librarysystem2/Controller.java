package ap.exercises.librarysystem2;

import ap.exercises.librarysystem2.model.*;
import ap.exercises.librarysystem2.repository.LibraryManager;
import ap.exercises.librarysystem2.utils.LoginStatus;
import ap.exercises.librarysystem2.utils.Session;
import ap.exercises.librarysystem2.utils.View;

import java.util.List;

import static java.lang.System.exit;


public class Controller {
    Library library;
    AuthenticationService authService;
    LibraryManager libraryManager;

    final int ID_LENGTH = Member.ID_LENGTH;
    final int PW_LENGTH = Member.PW_LENGTH;

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
            v.printLine("""
                    Choose a field to edit:
                    1. Title
                    2. Author
                    3. Pages
                    4. Publication year
                    5. Copies""");
            int option = v.readInt(1, 5);
            System.out.println("Enter new data: ");
            switch (option) {
                case 1 -> book.setTitle(v.askForBookTitle());
                case 2 -> book.setAuthor(v.readString());
                case 3 -> book.setPages(v.readInt(1, 5000));
                case 4 -> book.setPublicationYear(v.readInt(0, 3000));
                case 5 -> book.setCopies(v.readInt(0, 50));
            }
        } else {
            v.printLine("Book not found!");
        }
    }

    private void receiveReturnedBook() {
        int id = v.askForID(ID_LENGTH);
        String title = v.askForBookTitle();
        libraryManager.receiveReturnBook(id, title, session.getOperator());
    }

    private void acceptBorrowRequest() {
        System.out.println("Enter student ID: ");
        int id = v.readIntByLimit(ID_LENGTH);
        System.out.println("Enter book title: ");
        String title = v.readString();
        libraryManager.approveBorrowRequest(session.getOperator(), id, title);
    }

    private void studentStats() {
        System.out.println(libraryManager.composeOpStat(v.askForID(ID_LENGTH)));
    }


    private void searchBook() { //it has a list and a director NEEDS EDITING
        //show search book list
        v.printLine("""
                Search by:
                1. Title
                2. Author
                3. Publication Year
                0. Go Back
                """);
        int option = v.readInt(0, 3);
        List<Book> results = null;
        switch (option) {
            case 1 -> {
                System.out.print("Title: ");
                results = libraryManager.searchByTitle(v.readString());
            }
            case 2 -> {
                System.out.print("Author: ");
                results = libraryManager.searchByAuthor(v.readString());
            }
            case 3 -> {
                System.out.print("Publication year: ");
                results = libraryManager.searchByPublicationYear(v.readInt(0, 3000));
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
        v.printLine("Enter book name: ");
        String title = v.readString();
        libraryManager.requestBook(session.getStudent().getId(), title);
    }


    private void addBook() {
        libraryManager.addNewBook(v.getBookFullInfo());
    }


    // ----LOGINs

    private void guestLogin() {
        session.setLoginStatusToGuest();
    }

    private void operatorLogin() {
        int ID = v.askForID(ID_LENGTH);
        int password = v.askForPW(PW_LENGTH);
        session.setOperator(authService.OPLogin(ID, password));
    }

    private void managerLogin() {
        int ID = v.askForID(ID_LENGTH);
        int password = v.askForPW(PW_LENGTH);
        session.setManager(authService.managerLogin(ID, password));

    }

    private void studentLogin() {
        int ID = v.askForID(ID_LENGTH);
        int password = v.askForPW(PW_LENGTH);
        session.setStudent(authService.studentLogin(ID, password));
    }

    private void studentSignUp() {
        authService.studentSignUp(v.getStudentFullInfo(ID_LENGTH, PW_LENGTH));
    }

    private void logout() {
        v.printLine("You are logged out");
        session.logout();
    }


    //utils________

    private void saveAndExit() {
        libraryManager.save();
        exit(0);
    }



}

