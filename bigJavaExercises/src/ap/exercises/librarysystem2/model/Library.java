package ap.exercises.librarysystem2.model;

import ap.exercises.MidTermLib.LM.Borrow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private Manager manager;
    private String libName;
    private List<Book> bookList = new ArrayList<>();
    private Map<Integer, Student> studentMap = new HashMap<>();
    private Map<Integer, Operator> operatorMap = new HashMap<>();
    private List<Borrow> borrowList = new ArrayList<>();

    public Library (String libName, Manager manager){
        this(libName);
        this.manager = manager;
    }

    public Library(String libName) {
        this.libName = libName;
        operatorMap.put(10001, new Operator("OP01", "OP", 10001));
        operatorMap.put(10002, new Operator("OP02", "OP", 10002));

    }

    public void setManager(Manager manager) {
        this.manager = new Manager(manager.getFirstName(), manager.getLastName(), manager.getId(), manager.getEducation());
    }
    public Manager getManager() {
        return new Manager(manager.getFirstName(), manager.getLastName(), manager.getId(), manager.getEducation());
    }

    public String getLibName() {
        return libName;
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

    @Override
    public String toString() {
        return this.libName + "*" ;
    }

}