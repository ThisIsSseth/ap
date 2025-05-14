package ap.exercises.MidTermLib.LM.LibManTools;

import ap.exercises.MidTermLib.LM.Book;
import ap.exercises.MidTermLib.LM.Borrow;
import ap.exercises.MidTermLib.LM.Library;
import ap.exercises.MidTermLib.LM.Members.Member;
import ap.exercises.MidTermLib.LM.Members.Student;

public class LibraryManager {
    private Library library;
    private SignIn signIn = new SignIn();
    private enum Roles {Student, Manager, Operator;}
    private Roles role;
    private Member user;
    private boolean signInSuccess = false;
    private int maxOptions = 0;
    private SingleLibrarySaver saver;
    private Borrowing borrowing;


    public int getRole() {
        switch (role) {
            case Student:
                return 0;
            case Manager:
                return 1;
            case Operator:
                return 2;
            default:
                return 3;
        }
    }

    public String getUserName() {
        return user.getFirstName() + " " + user.getLastName();
    }

    public int getMaxOptions() {
        return maxOptions;
    }

    public LibraryManager(String libName) {
        this(new Library(libName));
    }

    public LibraryManager(Library library) {
        this.library = library;
        saver = new SingleLibrarySaver(library);

    }

    public boolean isSignInSuccess() {
        return signInSuccess;
    }

    public boolean managerEntryCheck(int password) {
        if (library.managerPasswordCheck(password)) {
            role = Roles.Manager;
            signInSuccess = true;
            user = library.getManager();
            return true;
        } else return false;
    }

    public boolean studentEntryCheck(int password, int id) {
        if (signIn.studentPasswordCheck(password, id, library.getStudentMap())) {
            role = Roles.Student;
            signInSuccess = true;
            user = library.getStudentMap().get(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean operatorEntryCheck(int password, int id) {
        if (signIn.operatorPasswordCheck(password, id, library.getOperatorMap())) {
            role = Roles.Operator;
            signInSuccess = true;
            user = library.getOperatorMap().get(id);
            return true;
        }
        return false;
    }

    public void addStudent(Student student) {
        signIn.addStudent(student, library.getStudentMap());
    }

    String studentMenu() {
        maxOptions = 5;
        return """
                1. Show the list of books
                2. Search a book by name
                3. Borrow a book by name
                4. Return a book
                5. See the borrowed books
                0. Exit""";
    }

    String managerMenu() {
        maxOptions = 10;
        return """
                1. Show the list of books
                2. Search a book by name
                3. Borrow a book by name
                4. Return a book
                5. The list of operators
                6. The list of students
                7. The list of borrowed books
                8. The list of overdue books
                9. The list of 10 most borrowed books
                10. See operator history
                0. Exit
                """;
    }

    String operatorMenu() {
        maxOptions = 10;
        return """
                1.  Show the list of books
                2.  Search a book by name
                3.  Borrow a book by name
                4.  Return a book
                5.  Edit and complete personal information
                6.  Add a book
                7.  Confirm pending borrow requests
                8.  Confirm pending return requests
                9.  See borrow history
                10. See student history
                0.  Exit""";
    }

    public String userMenu() {
        switch (role) {
            case Student -> {
                return studentMenu();
            }
            case Manager -> {
                return managerMenu();
            }
            case Operator -> {
                return operatorMenu();
            }
            default -> {
                exit();
                return "Sign In Failed. Please try again.\nExiting.";
            }
        }
    }

    public void exit() {
        user = null;
        role = null;
        signInSuccess = false;
        maxOptions = 0;

        //sign out
    }

//    public void optionReceiver(int option) {
//        if (role == Roles.Student) {
//            switch (option) {
//                case 1 -> {
//                    showBooks();
//                }
//                case 2 -> {
//                    searchBooks();
//                }
//            }
//        }
//    }


    public String bookMenu() {
        String bookMenu = "";
        for (Book book : library.getBookList()) {
            bookMenu += book.getTitle() + " | " + book.getAuthor() + " | Available:" + book.getCopies();
        }
        return bookMenu;
    }

    public Book searchBook(String bookName) {
        for (Book b : library.getBookList()) {
            if (b.getTitle().equals(bookName)) {
                return b;
            }
        }
        return null;
    }

    public void borrowingBook(String bookName) {
        Book book = searchBook(bookName);
        if (book.getCopies() > 0) {
            library.getBorrowList().add(borrowing.borrowABook(book, (Student) user, library.getOperatorMap()));
            book.borrowedACopy();
        } else {
            System.out.println("Book unavailable");
        }
    }

    public void returningBook(String bookName) {
        for (Borrow borrow : library.getBorrowList()) {
            if (borrow.getBook().getTitle().equals(bookName) && borrow.getStudent().equals(user)) {
                borrowing.returnABook(borrow, library.getOperatorMap());
                searchBook(bookName).returnedACopy();
                System.out.println("Done!");
                return;
            }
        }
        System.out.println("Book not found.");
    }
    public void save() {
        System.out.println("Saving changes...");
        saver.saveData();
    }
}
