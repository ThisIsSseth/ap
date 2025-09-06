package ap.exercises.librarysystem2;

import ap.exercises.librarysystem2.model.Library;
import ap.exercises.librarysystem2.model.Manager;
import ap.exercises.librarysystem2.model.Operator;
import ap.exercises.librarysystem2.model.Student;
import ap.exercises.librarysystem2.repository.LibraryManager;
import ap.exercises.librarysystem2.storage.FileInitializer;
import ap.exercises.librarysystem2.utils.AuthenticationService;
import ap.exercises.librarysystem2.utils.DefaultLibraryCreator;
import ap.exercises.librarysystem2.utils.InputReader;
import static java.lang.System.exit;

import java.io.File;


public class Controller {
    Library library = DefaultLibraryCreator.create();
    AuthenticationService authService = new AuthenticationService();
    LibraryManager libraryManager = new LibraryManager(library);

    InputReader inputReader = new InputReader();
    public enum LoginStatus {
        LOGGED_OUT,
        GUEST,
        STUDENT,
        OPERATOR,
        MANAGER
    }


    LoginStatus loginStatus = LoginStatus.LOGGED_OUT;

    Student student = null;
    Manager manager = null;
    Operator operator = null;


    public void startMenuDirector(int option) {
        switch (option) {
            case 1 -> studentSignUp();
            case 2 -> studentLogin();
            case 3 -> operatorLogin();
            case 4 -> managerLogin();
            case 5 -> guestLogin();
            case 6 -> saveAndExit();
        }
    }

    

    private void saveAndExit() {
        libraryManager.save();
        exit(0);
    }

    private void guestLogin() {
        loginStatus = LoginStatus.GUEST;
    }

    private void operatorLogin() {
        int ID = inputReader.readIntByLimit(5);
        int password = inputReader.readIntByLimit(4);
        operator = authService.OPLogin(ID, password);
        if(operator != null) {
            loginStatus = LoginStatus.OPERATOR;
        }
    }

    private void managerLogin() {
        int ID = inputReader.readIntByLimit(5);
        int password = inputReader.readIntByLimit(4);
        manager = authService.managerLogin(ID, password);
        if (manager != null) {
            loginStatus = LoginStatus.MANAGER;
        }
    }

    private void studentLogin() {
        int ID = inputReader.readIntByLimit(5);
        int password = inputReader.readIntByLimit(4);
        student = authService.studentLogin(ID, password);
        if (student != null) {
            loginStatus = LoginStatus.STUDENT;
        }
    }

    private void studentSignUp() {
        authService.studentSignUp(getStudentFullInfo());
    }

    private Student getStudentFullInfo() {
        System.out.println("Enter student name: ");
        String name = inputReader.readString();
        System.out.println("Enter student last name: ");
        String lastName = inputReader.readString();
        System.out.println("Enter student major: ");
        String major = inputReader.readString();
        System.out.println("Enter student password: ");
        int password = inputReader.readIntByLimit(4);
        System.out.println("Enter student ID: ");
        int ID = inputReader.readIntByLimit(5);
        return new Student(name, lastName, ID, major, password);
    }



}
