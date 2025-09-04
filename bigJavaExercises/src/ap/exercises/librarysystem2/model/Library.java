package ap.exercises.librarysystem2.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private String libraryName;
    private Manager manager;
    private List<Book> bookList = new ArrayList<>();
    private Map<Integer, Student> studentMap = new HashMap<>();
    private Map<Integer, Student> studentPendingSignUpMap = new HashMap<>();
    private Map<Integer, Operator> operatorMap = new HashMap<>();
    private List<Borrow> borrowList = new ArrayList<>();


    //for intial constructing
    public Library(String libName, Manager manager) {
        this.libraryName = libName;
        this.manager = manager;
    }

//    //for initial constructing w/ OPs
//    public Library(String libName) {
//        this.libraryName = libName;
//        operatorMap.put(10001, new Operator("OP01", "OP", 10001));
//        operatorMap.put(10002, new Operator("OP02", "OP", 10002));
//
//    }

    public void setManager(Manager manager) {
        this.manager = new Manager(manager.getFirstName(), manager.getLastName(), manager.getId(),manager.getPw(), manager.getEducation());
    }

    public Manager getManager() {
        return new Manager(manager.getFirstName(), manager.getLastName(), manager.getId(),manager.getPw(), manager.getEducation());
    }

    public String getLibraryName() {
        return libraryName;
    }

    public boolean managerPasswordCheck(int password) {
        return manager.passwordCheck(password);
    }

    public Map<Integer, Student> getStudentMap() {
        return studentMap;
    }

    public Map<Integer, Operator> getOperatorMap() {
        return operatorMap;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public List<Borrow> getBorrowList() {
        return borrowList;
    }

    public Map<Integer, Student> getStudentPendingSignUpMap() {
        return studentPendingSignUpMap;
    }

    public void setStudentPendingSignUpMap(Map<Integer, Student> studentPendingSignUpMap) {
        this.studentPendingSignUpMap = studentPendingSignUpMap;
    }

//    @Override
//    public String toString() {
//        return this.libraryName + "*";
//    }

}