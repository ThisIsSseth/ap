package ap.exercises.MidTermLib.LM.LibManTools;

import ap.exercises.MidTermLib.LM.Library;
import ap.exercises.MidTermLib.LM.Members.Member;
import ap.exercises.MidTermLib.LM.Members.Operator;
import ap.exercises.MidTermLib.LM.Members.Student;

public class LibraryManager {
    private Library library;
    private SignIn signIn = new SignIn();
    private enum Roles {Student, Manager, Operator;};
    private Roles role;
    private Member user;
    private boolean signInSuccess = false;
    private int maxOptions= 0;

    public Member getUser() {
        return user;
    }

    public int getMaxOptions() {
        return maxOptions;
    }

    public LibraryManager(Library library) {
        this.library = library;
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
        }
        else return false;
    }

    public boolean studentEntryCheck(int password, int id) {
        if (signIn.studentPasswordCheck(password, id, library.getStudentMap())) {
            role = Roles.Student;
            signInSuccess = true;
            user = library.getStudentMap().get(id);
            return true;
        } else {return false;}
    }

    public boolean operatorEntryCheck(int password, int id) {
        if (signIn.operatorPasswordCheck(password, id, library.getOperatorMap())){
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
                3. See the borrows books
                4. Borrow a book by name
                5. Return a book
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
        maxOptions = 9;
        return """
                1. Show the list of books
                2. Search a book by name
                3. Borrow a book by name
                4. Edit and complete personal information
                5. Add a book
                6. Confirm pending borrow requests
                7. Confirm pending return requests
                8. See borrow history
                9. See student history
                0. Exit""";
    }

    public String userMenu() {
        switch (role){
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

    public void exit(){
        //sign out
    }

    public void optionReceiver(int option) {
        if (role == Roles.Student) {
            switch (option){
                case 1 -> {

                }
            }
        }


    }

    private void showBooks(){
        /*I want to show books only 10 at once and then going to the next page
        * So there should be a variable holding pages
        * and each time we are showing menus of stuff, it should add one to the page
        *
        *
        * */
    }


}
