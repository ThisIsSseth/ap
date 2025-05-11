package ap.exercises.MidTermLib.LM.LibManTools;

import ap.exercises.MidTermLib.LM.Library;
import ap.exercises.MidTermLib.LM.Members.Student;

public class LibraryManager {
    private Library library;
    private SignIn signIn = new SignIn();
    private boolean signInSuccess = false;
    private final boolean Student = false;
    private final boolean Manager = true;
    private boolean role;

    public LibraryManager(Library library) {
        this.library = library;
    }


    public boolean managerEntryCheck(int password) {
        if (library.managerPasswordCheck(password)) {
            role = Manager;
            signInSuccess = true;
            return true;
        }
        else return false;
    }

    public boolean studentEntryCheck(int password, int id) {
        if (signIn.studentPasswordCheck(password, id, library.getStudentList())) {
            role = Student;
            signInSuccess = true;
            return true;
        } else {return false;}
    }

    public void addStudent(Student student) {
        signIn.addStudent(student, library.getStudentList());
    }

}
