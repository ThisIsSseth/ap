package ap.exercises.librarysystem2.utils;

import ap.exercises.librarysystem2.model.Manager;
import ap.exercises.librarysystem2.model.Operator;
import ap.exercises.librarysystem2.model.Student;

public class Session {
    private LoginStatus loginStatus = LoginStatus.LOGGED_OUT;

    public void setStudent(Student student) {
        clear();
        this.student = student;
        if (student != null) {
            loginStatus = LoginStatus.STUDENT;
        }
    }

    public void setManager(Manager manager) {
        clear();
        this.manager = manager;
        if (manager != null)
            this.loginStatus = LoginStatus.MANAGER;
    }

    public void setOperator(Operator operator) {
        clear();
        this.operator = operator;
        if (operator != null)
            loginStatus = LoginStatus.OPERATOR;
    }

    Student student = null;
    Manager manager = null;
    Operator operator = null;

    public Student getStudent() {
        return student;
    }

    public Manager getManager() {
        return manager;
    }

    public Operator getOperator() {
        return operator;
    }

    public LoginStatus getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatusToGuest() {
        clear();
        loginStatus = LoginStatus.GUEST;
    }

    public void logout() {
        clear();
        loginStatus = LoginStatus.LOGGED_OUT;
    }

    void clear(){
        operator = null;
        manager = null;
        student = null;
    }


//    public void setLoginStatus(LoginStatus loginStatus) {
//        this.loginStatus = loginStatus;
//    }

}
