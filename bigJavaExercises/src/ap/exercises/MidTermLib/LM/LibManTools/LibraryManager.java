package ap.exercises.MidTermLib.LM.LibManTools;

import ap.exercises.MidTermLib.model.Book;
import ap.exercises.MidTermLib.LM.Borrow;
import ap.exercises.MidTermLib.model.Library;
import ap.exercises.MidTermLib.model.Manager;
import ap.exercises.MidTermLib.model.Member;
import ap.exercises.MidTermLib.model.Operator;
import ap.exercises.MidTermLib.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    private Library library;
    private SignIn signIn = new SignIn();

    private enum Roles {Student, Manager, Operator;}

    private Roles role;

    private Member user;

    private boolean signInSuccess = false;
    private int maxOptions = 0;
    private SingleLibrarySaver saver;
    private Borrowing borrowing = new Borrowing();
    List<Borrow> borrowPending = new ArrayList<>();


    public LibraryManager(String libName, Manager manager) {
        this(new Library(libName), manager);
        saver = new SingleLibrarySaver(library);
        saver.loadData();
    }

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

    public LibraryManager(Library library, Manager manager) {
        this.library = library;
        saver = new SingleLibrarySaver(library);
        library.setManager(manager);

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

    public void doStudentOption1() {
        System.out.println(showBooks());
    }

    public void doStudentOption2(String bookName) {
        Book book = searchBook(bookName);
        if (book == null) {
            System.out.println("Book not found.");
        } else {
            System.out.println("Book found:");
            System.out.println(book.getTitle() + " | " + book.getAuthor() + " | Available:" + book.getCopies());
        }
    }

    public void doStudentOption3(String bookName) {
        Book book = searchBook(bookName);
        if (book == null) {
            System.out.println("Book not found.");
        } else {
            borrowingBook(book);
            System.out.println("Your request is pending.");
        }
    }

    public void doStudentOption4(String bookName) {
        Book book = searchBook(bookName);
        if (book == null) {
            System.out.println("Book not found.");
        }
        for (Borrow b : library.getBorrowList()) {
            if (b.getBook().getTitle().equals(bookName) && b.getStudent().equals(user)) {
                b.returnByStudentRequest();
                System.out.println("Your request is pending.");
                return;
            }
        }
        System.out.println("Book was not on your borrow list.");
    }

    public void doStudentOption5() {
        System.out.println(studentBorrowList());
    }

    private String studentBorrowList() {
        String list = "";
        for (Borrow b : borrowPending) {
            if(b.getStudent().equals(user)) {
                list += b.getBook().getTitle() +" | " + b.getStudent().getLastName() + " | " + b.getStudent().getFirstName() + (b.getBorrowDate() == null? "Borrow request is pending." : " | " + b.getBorrowDate()) +
                        (b.getBorrowDate() == null? "" : " | " + b.getBorrowDate().plusDays(10)) + (b.getRealReturnDate() == null? "" : " | " + b.getRealReturnDate());
            }
        }
        return list;
    }

    String managerMenu() {
        maxOptions = 9;
        return """
                1. Show the list of books
                2. Search a book by name
                3. The list of operators
                4. The list of students
                5. The list of borrowed books
                6. The list of overdue books
                7. The list of 10 most borrowed books
                8. See operator history
                9. Add Operator
                0. Exit
                """;
    }

    public void doManagerOption3() {
        System.out.println(showOperators() == "" ? "Nothing to show\n" : showOperators());
    }

    public void doManagerOption4() {
        System.out.println(showStudents()== "" ? "Nothing to show\n" : showStudents());
    }

    public void doManagerOption5() {
        System.out.println(showBorrows() == "" ? "Nothing to show\n" : showBorrows());
    }

    public void doManagerOption6() {
        System.out.println(showOverdues()== "" ? "Nothing to show\n" : showOverdues());
    }

    public void doManagerOption7(){
        System.out.println(showTenMostBorrows() == "" ? "Nothing to show\n" : showTenMostBorrows());
    }

    public void doManagerOption8(int opName){
        System.out.println(showOpHistory(opName) == "" ? "Nothing to show\n" : showOpHistory(opName));
    }

    private String showOpHistory(int opId) {
        String opHistory = "";
        Operator operator = null;
        for (Operator op : library.getOperatorMap().values()){
            if (op.getId() == opId){
                operator = op;
                break;
            }
        }
        try {
            for (Borrow b : library.getBorrowList()) {
                if (b.getGiverOperator().equals(operator)) {
                    opHistory += "\n" + operator.getFirstName() + " | " + operator.getLastName() + " | " + operator.getId() +
                            " | Accepted borrow request:" + b.getBook().getTitle() + ", by "+ b.getStudent().getId();
                }
            }
        }catch (NullPointerException e){
            System.out.println("Operator not found.");
        }
        return opHistory;
    }


    public void doManagerOption9(String opFName, String oPLName, int opId) {
        Operator op = new Operator(opFName, oPLName, opId);
        if (!library.getOperatorMap().containsKey(op.getId())) {
            library.getOperatorMap().put(op.getId(), op);
            System.out.println("Operator added.");
        } else {
            System.out.println("Operator with this ID already exists.");
        }
    }

    String operatorMenu() {
        maxOptions = 8;
        return """
                1.  Show the list of books
                2.  Search a book by name
                3.  Edit and complete personal information
                4.  Add a book
                5.  Confirm pending borrow requests
                6.  Confirm pending return requests
                7.  See borrow history
                8.  See student history
                0.  Exit""";
    }

    public void doOperatorOption3(int option, String name) {
        if (option == 1) {
            library.getOperatorMap().get(user.getId()).setFirstName(name);
        } else {
            library.getOperatorMap().get(user.getId()).setLastName(name);
        }
    }

    public void doOperatorOption4(String title, String author, int pages, int publicationYear, int copies) {
        Book book = new Book(title, author, pages, publicationYear, copies);
        if (searchBook(book.getTitle()) != null) {
            System.out.println("Book already exists.");
        } else {
            library.getBookList().add(book);
        }
    }

    /**
     * Shows the list of pending borrow requests*/
    public int doOperatorOption5_1() {
        borrowPending.clear();
//        int i = 1;
//        Book book;
//        List<Borrow> tempBorrowList = library.getBorrowList();
//        while (true) {
//            if (!tempBorrowList.get(i - 1).getConfirmationByOperator()) {
//                book = tempBorrowList.get(i - 1).getBook();
//                System.out.println(book.getTitle() + " | " + book.getAuthor());
//                System.out.println("Requested by" + library.getBorrowList().get(i - 1).getStudent().getId());
//            }
//        } //must be done in the borrowing by a function...
        int i = 1;
        for (Borrow borrow : library.getBorrowList()) {
            if(borrow.getGiverOperator() == null) {
                borrowPending.add(borrow);
                System.out.println(i + ") " + borrow.getBook().getTitle() + " | "+ borrow.getStudent().getLastName() + " " + borrow.getStudent().getFirstName() + " " + borrow.getStudent().getId());
                i ++;
            }
        }
        return borrowPending.size();
    }

    public void doOperatorOption5_2(int index) {
        if(borrowPending != null) {
            if (index > borrowPending.size())
                System.out.println("Out of range. Please enter a number between 1 and " + borrowPending.size());
            else {
                borrowing.confirmBorrow((Operator) user, borrowPending.get(index - 1));
            }
        }else {
            System.out.println("Nothing to show");
        }
    }

    public String getUserMenu() {
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
        saver.saveData();
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

    public String showBooks() {
        String bookMenu = "";
        for (Book book : library.getBookList()) {
            bookMenu += "\n"+ book.getTitle() + " | " + book.getAuthor() + " | Available:" + book.getCopies();
        }
        return bookMenu;
    }

    public String showOperators() {
        String operatorMenu = "";
        for (Operator op : library.getOperatorMap().values()) {
            operatorMenu += "\n" + op.getFirstName() + " | " + op.getLastName() + " | " + op.getId();
        }
        return operatorMenu;
    }

    private String showStudents() {
        String students = "";
        for (Student s : library.getStudentMap().values()){
            students += "\n" + s.getFirstName() + " | " + s.getLastName() + " | " + s.getId();
        }
        return students;
    }

    private String showBorrows(){
        String borrows = "";
        for (Borrow b : library.getBorrowList()){
            borrows += b.getBook().getTitle() +" | " + b.getStudent().getLastName() + " | " + b.getStudent().getFirstName() + (b.getBorrowDate() == null? "" : " | " + b.getBorrowDate());
        }
        return borrows;
    }

    private String showOverdues(){
        String overdues = "";
        for (Borrow b : library.getBorrowList()){
            if ( b.getBorrowDate() != null && !b.getBorrowDate().isBefore(LocalDate.now())) {
                overdues += b.getBook().getTitle() +" | " + b.getStudent().getLastName() + " | " + b.getStudent().getFirstName() + (b.getBorrowDate() == null? "" : " | " + b.getBorrowDate());
            }
        }
        return overdues;
    }

    private String showTenMostBorrows(){
        String tenMost = "";
        for (Borrow b : /*tenMost*/(library.getBorrowList())){

        }
    return tenMost;
    }

//    private List<Borrow> tenMost(List<Borrow> list){
//        int count = 0;
//        Borrow most;
//        List<Borrow> tenMost = new ArrayList<>();
//        for (Borrow b : list){
//            for (Borrow c : list){
//                if (b.equals(c)){
//                    count++;
//                }
//            }
//        }
//    }

    public Book searchBook(String bookName) {
//        for (Book b : library.getBookList()) {
//            if (b.getTitle().equals(bookName)) {
//                return b;
//            }
//        }
        for (int i = 0; i < library.getBookList().size(); i++) {
            if (library.getBookList().get(i).getTitle().equals(bookName)) {
                return library.getBookList().get(i);
            }
        }
        return null;
    }

    void borrowingBook(Book book) {
        if (book != null) {
            if (book.getCopies() > 0) {
                library.getBorrowList().add(borrowing.borrowABook(book, (Student) user, library.getOperatorMap()));
                book.substituteCopyByOne();
            } else {
                System.out.println("Book unavailable");
            }
        }
    }


    /**This one is for the use of op*/
    public void returningBook(String bookName) {
        for (Borrow borrow : library.getBorrowList()) {
            if (borrow.getBook().getTitle().equals(bookName) && borrow.getStudent().equals(user) && user instanceof Operator) {
                borrowing.returnABook(borrow, (Operator) user);
                searchBook(bookName).addCopyByOne();
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