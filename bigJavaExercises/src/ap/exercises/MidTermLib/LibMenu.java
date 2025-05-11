package ap.exercises.MidTermLib;

import ap.exercises.MidTermLib.LM.DefaultCreator;
import ap.exercises.MidTermLib.LM.Library;
import ap.exercises.MidTermLib.LM.LibManTools.LibraryManager;
import ap.exercises.MidTermLib.LM.Members.Student;

import java.util.List;
import java.util.Scanner;

public class LibMenu {
    DefaultCreator defaultCreator = new DefaultCreator();
    Library currentLibrary;
    LibraryManager libraryManager;
    List<Library> libraryList;

    public void LibraryMenu() { //Shows libraries to choose from
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Choose a Library:");
            int indexOfLibrary = 1;
            for (Library library : libraryList) {
                System.out.println(indexOfLibrary + "_ " + library.getLibName());
                indexOfLibrary++;
            }
            indexOfLibrary = scanner.nextInt();
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Enter your role:
                1. Manager
                2. Student""");
        int choice = scanner.nextInt();
        try {
            scanner.nextLine();
        } catch (NullPointerException e) {
            System.out.println("MenuError1");
        }

        switch (choice) {
            case 1 -> {
                System.out.println("Enter your password: ");
                int password = scanner.nextInt();
                if (libraryManager.managerEntryCheck(password)) {
                    System.out.println("Sign in successful.\nWelcome");
                } else {
                    System.out.println("Invalid password. Try again.");
                }
            }
            case 2 -> {
                signUp(libraryManager);
                System.out.println("Enter your id:");
                int id = scanner.nextInt();
                System.out.println("Enter your password:");
                int password = scanner.nextInt();
                if (libraryManager.studentEntryCheck(password, id)) {
                    System.out.println("Sign in successful.\nWelcome");
                } else {
                    System.out.println("Invalid password. Try again. Or sign up");
                }
            }

        }
    }

    void signUp(LibraryManager libraryManager) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to Sign Up? (1.y/2.n)");
        int signUpChoice = scanner.nextInt();
        switch (signUpChoice) {
            case 1 -> {
                System.out.println("Enter your first name:");
                String firstName = scanner.nextLine();
                System.out.println("Enter your last name:");
                String lastName = scanner.nextLine();
                System.out.println("Enter your id:");
                int id = scanner.nextInt();
                System.out.println("Enter your major:");
                String major = scanner.nextLine();
                System.out.println("Enter your password:");
                int password = scanner.nextInt();
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


}
