package ap.exercises.MidTermLib;

import ap.exercises.MidTermLib.LM.DefaultCreator;
import ap.exercises.MidTermLib.LM.Library;
import ap.exercises.MidTermLib.LM.LibManTools.LibraryManager;
import ap.exercises.MidTermLib.LM.Members.Student;

import java.util.List;

public class LibMenu {
    DefaultCreator defaultCreator = new DefaultCreator();
    Library currentLibrary;
    LibraryManager libraryManager;
    List<Library> libraryList;
    InputReader inputReader = new InputReader();

    public void LibraryMenu() { //Shows libraries to choose from
        try {
            System.out.println("Choose a Library:");
            int indexOfLibrary = 1;
            for (Library library : libraryList) {
                System.out.println(indexOfLibrary + "_ " + library.getLibName());
                indexOfLibrary++;
            }
            indexOfLibrary = inputReader.readInt(1, libraryList.size());
            currentLibrary = libraryList.get(indexOfLibrary - 1);
        } catch (NullPointerException e) {
            System.out.println("No library found.\nEntering default library...");
            libraryList.add(defaultCreator.defaultLibrary());
            currentLibrary = libraryList.getFirst();
        }
    }

    public void loadLibraryData (){
        // read files and load libraries to libList

    }

    public void signInToLibrary() {
        libraryManager = new LibraryManager(currentLibrary);
        signInMenu(libraryManager);

    }

    /**
     * Sign in system
     */
    void signInMenu(LibraryManager libraryManager) {
        System.out.println("""
                Enter your role:
                1. Manager
                2. Student
                3. Operator""");
        int choice = inputReader.readInt(1, 3);

        switch (choice) {
            case 1 -> {
                System.out.println("Enter your password: ");
                int password = inputReader.readPassword();
                if (libraryManager.managerEntryCheck(password)) {
                    System.out.println("Sign in successful.\nWelcome");
                } else {
                    System.out.println("Invalid password. Try again.");
                }
            }
            case 2 -> {
                signUp(libraryManager);
                System.out.println("Enter your id:");
                int id = inputReader.readID();
                System.out.println("Enter your password:");
                int password = inputReader.readPassword();
                if (libraryManager.studentEntryCheck(password, id)) {
                    System.out.println("Sign in successful.\nWelcome");
                } else {
                    System.out.println("Invalid password. Try again. Or sign up");
                }
            }
            case 3 -> {System.out.println("Enter your id:");
                int id = inputReader.readID();
                System.out.println("Enter your password:");
                int password = inputReader.readPassword();
                if (libraryManager.operatorEntryCheck(password, id)) {
                    System.out.println("Sign in successful.\nWelcome");
                } else {
                    System.out.println("Invalid password ot ID. Try again.");
                }

            }

        }
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
            case 2 -> {}
        }
    }

    public void userMenu() {
        System.out.println(libraryManager.userMenu());
        System.out.println("Choose an option: ");
        int option = inputReader.readInt(0, libraryManager.getMaxOptions());

    }



}
