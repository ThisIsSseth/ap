package ap.exercises.MidTermLib;

import ap.exercises.MidTermLib.LM.DefaultCreator;
import ap.exercises.MidTermLib.LM.LibManTools.LibraryManager;
import ap.exercises.MidTermLib.model.Manager;
import ap.exercises.MidTermLib.model.Student;

import java.util.ArrayList;
import java.util.List;

 class Menu {
    private InputReader inputReader = new InputReader();
    private MultiLibrariesSaver libsSaver = new MultiLibrariesSaver();
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
    public boolean LibraryMenu() {
        if (libraryNameList != null && !libraryNameList.isEmpty()) {
            System.out.println("--------\nChoose a Library or enter 0 to exit:");
            int indexOfLibrary = 1;
            for (String libraryName : libraryNameList) {
                System.out.println(indexOfLibrary + ") " + libraryName.split("@")[0]);
                indexOfLibrary++;
            }
            indexOfLibrary = inputReader.readInt(0, libraryNameList.size());
            if (indexOfLibrary == 0) {
                return false;
            }
            String[] b = libraryNameList.get(indexOfLibrary - 1).split("@");
            currentLibraryName = b[0];
            Manager manager = new Manager(b[1], b[2], Integer.parseInt(b[3]), b[4]);
            libraryManager = new LibraryManager(currentLibraryName, defaultCreator.defaultLibrary().getManager());
        } else {
            System.out.println("No Valid library found. Entering default library...");
            currentLibraryName = defaultCreator.defaultLibrary().getLibName() + "@" + defaultCreator.defaultLibrary().getManager()    ;
            libraryNameList.add(currentLibraryName);
            libraryManager = new LibraryManager(defaultCreator.defaultLibrary(), defaultCreator.defaultLibrary().getManager());
        }
        return true;
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
        int choice = inputReader.readInt(0, 3);

        switch (choice) {
            case 1 -> {
                System.out.println("Enter your password: ");
                int password = inputReader.readPassword();
                if (libraryManager.managerEntryCheck(password)) {
                    System.out.println("Sign in successful.");
                } else {
                    System.out.println("Invalid password. Try again.");
                    return false;
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
                    signUp();
                    return false;
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
                    return false;
                }
            }
            case 0 -> {
                return false;
            }
        }
        return true;
    }

    void signUp() {
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
        System.out.println("\n~~~\nWelcome " + libraryManager.getUserName() + "\n"+
                libraryManager.getUserMenu() + "\nChoose an option: ");
    }

    private boolean operatorService() {
        commonUserMenu();
        int option = inputReader.readInt(0, libraryManager.getMaxOptions());
        switch (option) {
            case 1 -> {libraryManager.doStudentOption1();
            }
            case 2 -> {
                System.out.println("Enter the name of the book:");
                String bookName = inputReader.readString();
                libraryManager.doStudentOption2(bookName);
            }

            case 3 -> {
                System.out.println("What do you want to do?\n1. Edit First Name\n2. Edit Last Name\n");
                int option1 = inputReader.readInt(1, 2);
                System.out.println("Enter the name:");
                String name = inputReader.readString();
                libraryManager.doOperatorOption3(option1, name);
            }

            case 4 -> {
                System.out.println("Enter the name of the book:");
                String bookName = inputReader.readString();
                System.out.println("Enter the author:");
                String author = inputReader.readString();
                System.out.println("Enter the publication year:");
                int publicationYear = inputReader.readInt(0, 2025);
                System.out.println("Enter pages:");
                int pages = inputReader.readInt(1, 2000);
                System.out.println("Enter the number of copies:");
                int copies = inputReader.readInt(1, 25);
                libraryManager.doOperatorOption4(bookName, author, pages, publicationYear, copies);
            }
            case 5 -> {
                int maxSize = libraryManager.doOperatorOption5_1();
                int option1 = Integer.MAX_VALUE;
                System.out.println("Enter the index, 0 to exit: ");
                option1 = inputReader.readInt(0, maxSize);
                while (option1 > 0) {
                    libraryManager.doOperatorOption5_2(option1);
                    System.out.println("Enter the next index or 0 to exit: ");
                    option1 = inputReader.readInt(0, maxSize);
                }
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

        switch (option){
            case 1 ->{libraryManager.doStudentOption1();

            }
            case 2 -> {
                System.out.println("Enter the name of the book:");
                String bookName = inputReader.readString();
                libraryManager.doStudentOption2(bookName);
            }

            case 3 -> {libraryManager.doManagerOption3();}

            case 4 -> {libraryManager.doManagerOption4();}

            case 5 ->{libraryManager.doManagerOption5();}

            case 6 -> {libraryManager.doManagerOption6();}

            case 7 -> {}


            case 9 -> {
                System.out.println("Enter the first name of the new operator:");
                String opFirstName = inputReader.readString();
                System.out.println("Enter the last name of the new operator:");
                String opLastName = inputReader.readString();
                System.out.println("Enter OP ID:");
                int opId = inputReader.readID();
                libraryManager.doManagerOption9(opFirstName,opLastName,opId);
            }

            case 0 -> {libraryManager.exit();
            return false;}
        }

        return true;
    }

    private boolean studentService() {
        commonUserMenu();
        int option = inputReader.readInt(0, libraryManager.getMaxOptions());
        switch (option) {
            case 1 -> { libraryManager.doStudentOption1();
            }
            case 2 -> {
                System.out.println("Enter the name of the book: ");
                String bookName = inputReader.readString();
                libraryManager.doStudentOption2(bookName);
            }
            case 3 -> {
                System.out.println("Enter the name of the book you want to borrow: ");
                String bookName = inputReader.readString();
                libraryManager.doStudentOption3(bookName);
            }
            case 4 -> {
                System.out.println("Enter the name of the book you want to return: ");
                String bookName = inputReader.readString();
                libraryManager.doStudentOption4(bookName);

            }
            case 5 -> {
                libraryManager.doStudentOption5();
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