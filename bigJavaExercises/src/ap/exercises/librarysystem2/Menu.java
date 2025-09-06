package ap.exercises.librarysystem2;

import javax.xml.stream.events.StartDocument;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    List<String> StartMenu = new ArrayList<String>();
    List<String> StudentMenu = new ArrayList<String>();
    List<String> OpMenu = new ArrayList<String>();
    List<String> ManagerMenu = new ArrayList<String>();
    List<String> GuestMenu = new ArrayList<String>();


    enum menus {start, student, op, manager};


    //Please check for accuracy of option between Menu and Main
    public Menu() {
        StartMenu.add("SignUp as Student");
        StartMenu.add("Login as Student");
        StartMenu.add("Login as Operator");
        StartMenu.add("Login as Manager");
        StartMenu.add("Login as Guest");
        StartMenu.add("Exit");

        StudentMenu.add("Search book");
        StudentMenu.add("Request book");

        GuestMenu.add("See total number of members(students)");
        GuestMenu.add("Search book");
        GuestMenu.add("See statics");

        OpMenu.add("Add book");
        OpMenu.add("Search book");
        OpMenu.add("Edit book info");
        OpMenu.add("Accept borrow request");
        OpMenu.add("Receive returned book");
        OpMenu.add("Activate user");
        OpMenu.add("Deactivate user");
        OpMenu.add("See a student's statistics");
        OpMenu.add("See advanced statistics");

        ManagerMenu.add("Add operator");
        ManagerMenu.add("See Operator's statistics");
        ManagerMenu.add("See a student's statistics");


    }

    public void showStartMenu() {
        show(StartMenu);
    }

    public Integer getStartMenuSize() {
        return StartMenu.size();
    }

    public void showStudentMenu() {
        show(StudentMenu);
    }

    public Integer getStudentMenuSize() {
        return StudentMenu.size();
    }

    public void showOpMenu() {
        show(OpMenu);
    }

    public Integer getOpMenuSize() {
        return OpMenu.size();
    }


    public void showManagerMenu() {
        show(ManagerMenu);
    }

    public Integer getManagerMenuSize() {
        return ManagerMenu.size();
    }

    private void show(List<String> currentMenu) {
        for (int i = 0; i < currentMenu.size(); i++) {
            System.out.println(i + 1 + ". " + currentMenu.get(i));
        }
        System.out.println("-----------");
    }
}
