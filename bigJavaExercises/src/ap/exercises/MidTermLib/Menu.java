package ap.exercises.MidTermLib;

import ap.exercises.MidTermLib.LM.Book;
import ap.exercises.MidTermLib.LM.DefaultCreator;
import ap.exercises.MidTermLib.LM.LibManTools.LibraryManager;
import ap.exercises.MidTermLib.LM.Members.Student;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private InputReader inputReader = new InputReader();
    private MultiLibrariesSavor libsSaver = new MultiLibrariesSavor();
    private List<String> libraryNameList = new ArrayList<>();
    private String currentLibraryName;
    private LibraryManager libraryManager;
    private DefaultCreator defaultCreator = new DefaultCreator();

    public void loadMultiLibrariesData() {
        libraryNameList = libsSaver.load();
    }

    /**
     * Shows libraries to choose from
     */
    public void LibraryMenu() {
        if (libraryNameList != null && !libraryNameList.isEmpty()) {
            System.out.println("Choose a Library:");
            int indexOfLibrary = 1;
            for (String libraryName : libraryNameList) {
                System.out.println(indexOfLibrary + ") " + libraryName);
                indexOfLibrary++;
            }
            indexOfLibrary = inputReader.readInt(1, libraryNameList.size());
            currentLibraryName = libraryNameList.get(indexOfLibrary - 1);
        } else {
            System.out.println("No Valid library found. Entering default library...");
            currentLibraryName = defaultCreator.defaultLibrary().getLibName();
            libraryNameList.add(currentLibraryName);
        }
        libraryManager = new LibraryManager(defaultCreator.defaultLibrary());
    }

    /**
     * Sign in system
     */
    public boolean signInToLibrary() {
        System.out.println("""
                Enter your role:
                1. Manager
                2. Student
                3. Operator
                0. Exit""");
        int choice = inputReader.readInt(1, 3);

        switch (choice) {
            case 1 -> {
                System.out.println("Enter your password: ");
                int password = inputReader.readPassword();
                if (libraryManager.managerEntryCheck(password)) {
                    System.out.println("Sign in successful.");
                } else {
                    System.out.println("Invalid password. Try again.");

                }
            }
            case 2 -> {
                System.out.println("Enter your id:");
                int id = inputReader.readID();
                System.out.println("Enter your password:");
                int password = inputReader.readPassword();
                if (libraryManager.studentEntryCheck(password, id)) {
                    System.out.println("Sign in successful.");
                } else {
                    System.out.println("Invalid password. Try again. Or sign up");
                    signUp(libraryManager);
                }
            }
            case 3 -> {
                System.out.println("Enter your id:");
                int id = inputReader.readID();
                System.out.println("Enter your password:");
                int password = inputReader.readPassword();
                if (libraryManager.operatorEntryCheck(password, id)) {
                    System.out.println("Sign in successful.");
                } else {
                    System.out.println("Invalid password ot ID. Try again.");
                }
            }
            case 0 -> {
                return false;
            }
        }
        return true;
    }

    void signUp(LibraryManager libraryManager) {
        System.out.println("Do you want to Sign Up? (1.y/2.n)");
        int signUpChoice = inputReader.readInt(1, 2);
        switch (signUpChoice) {
            case 1 -> {
                System.out.println("Enter your first name:");
                String firstName = inputReader.readString();
                System.out.println("Enter your last name:");
                String lastName = inputReader.readString();
                System.out.println("Enter your id:");
                int id = inputReader.readID();
                System.out.println("Enter your major:");
                String major = inputReader.readString();
                System.out.println("Enter your password:");
                int password = inputReader.readPassword();
                Student student = new Student(firstName, lastName, id, major, password);
                if (!libraryManager.studentEntryCheck(password, id)) {
                    libraryManager.addStudent(student);
                } else {
                    System.out.println("There is already a student with this id and password.");
                }
            }
            case 2 -> {
            }
        }
    }

    public boolean userMenu() {
        switch (libraryManager.getRole()) {
            case 0 -> {
                return studentService();
            }
            case 1 -> {
                return managerService();
            }
            case 2 -> {
                return operatorService();
            }
            default -> {
                System.out.println("Something went wrong :(");
                return false;
            }
        }
    }

    void commonUserMenu() {
        System.out.println("Welcome " + libraryManager.getUserName() +
                libraryManager.userMenu() + "Choose an option: ");
    }

    private boolean operatorService() {
        commonUserMenu();
        int option = inputReader.readInt(0, libraryManager.getMaxOptions());
        switch (option){
            case 1 -> {

            }

            case 0 -> {
                exit();
                return false;
            }
        }
        return true;
    }

    private boolean managerService() {
        commonUserMenu();
        int option = inputReader.readInt(0, libraryManager.getMaxOptions());
        return true;
    }

    private boolean studentService() {
        commonUserMenu();
        int option = inputReader.readInt(0, libraryManager.getMaxOptions());
        switch (option) {
            case 1 -> {
                System.out.println(libraryManager.bookMenu());
            }
            case 2 -> {
                String bookName = inputReader.readString();
                Book book = libraryManager.searchBook(bookName);
                if (book == null) {
                    System.out.println("Book not found.");
                } else {
                    System.out.println("Book found:");
                    System.out.println(book.getTitle() + " | " + book.getAuthor() + " | Available:" + book.getCopies());
                    System.out.println("Do you want to borrow it?[1.y/2.n]");
                }
            }
            case 3 -> {
                System.out.println("Enter the name of the book you want to borrow: ");
                String bookName = inputReader.readString();
                Book book = libraryManager.searchBook(bookName);
                if (book == null) {
                    System.out.println("Book not found.");
                } else {
                    libraryManager.borrowingBook(bookName);
                }
            }
            case 4 -> {

            }
            case 5 -> {
                System.out.println("Enter the name of the book you want to return: ");
                String bookName = inputReader.readString();
                libraryManager.returningBook(bookName);
            }
            case 0 -> {
                exit();
                return false;
            }
        }
        return true;
    }


    void saveLibNames() {
        String list = "";
        for (String s : libraryNameList) {
            list += s + "\n";
        }
        libsSaver.save(list);
    }


    private void exit() {
        System.out.println("Are you sure?[1.y/2.n]");
        int option = inputReader.readInt(1, 2);
        switch (option) {
            case 1 -> {
                System.out.println("Saving data...");
                saveLibNames();
                libraryManager.save();
            }
        }
    }
}
