package ap.exercises.librarysystem2.utils;

import ap.exercises.librarysystem2.model.Library;
import ap.exercises.librarysystem2.model.Manager;
import ap.exercises.librarysystem2.model.Operator;
import ap.exercises.librarysystem2.model.Student;

public class AuthenticationService {

    Library library;

    public AuthenticationService(Library library) {
        this.library = library;
    }

    public void studentSignUp(Student student) {
        if (library.getStudentMap().containsKey(student.getId())) {
            System.out.println("Student id already exists. Please Login.");
        } else if (!library.getStudentPendingSignUpMap().containsKey(student.getId())) {
            library.getStudentPendingSignUpMap().put(student.getId(), student);
        } else System.out.println("You have already requested sign up.");
    }

    public Student studentLogin(int id, int pw) {
        if (library.getStudentMap().containsKey(id)) {
            if (library.getStudentMap().get(id).getPw() == pw) {
                return library.getStudentMap().get(id);
            }
            System.out.println("Wrong password.");
            return null;
        }
        System.out.println("ID not found.");
        return null;
    }

    public Operator OPLogin(int id, int pw) {
        if (library.getOperatorMap().containsKey(id)) {
            if (library.getOperatorMap().get(id).getPw() == pw) {
                return library.getOperatorMap().get(id);
            }
            System.out.println("Wrong password.");
            return null;
        }
        System.out.println("ID not found.");
        return null;
    }

    public Manager managerLogin(int id, int pw) {
        if (library.managerPasswordCheck(pw) && library.getManager().getId() == id) return library.getManager();
        return null;
    }

}
