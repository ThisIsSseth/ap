package ap.exercises.librarysystem2;

import javax.xml.stream.events.StartDocument;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<String> StartMenu = new ArrayList<String>();
    private List<String> StudentMenu = new ArrayList<String>();
    private List<String> OpMenu = new ArrayList<String>();
    private List<String> ManagerMenu = new ArrayList<String>();
    private List<String> GuestMenu = new ArrayList<String>();
    private List<String> BookSearchMenu = new ArrayList<String>();




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

        OpMenu.add("Add book");
        OpMenu.add("Search book");
        OpMenu.add("Edit book info");
        OpMenu.add("Accept borrow request");
        OpMenu.add("Receive returned book");
        OpMenu.add("Activate user");
        OpMenu.add("Deactivate user");
        OpMenu.add("See a student's statistics");
        OpMenu.add("See advanced statistics(currently unavailable)");
        OpMenu.add("Change password");

        ManagerMenu.add("Add operator");
        ManagerMenu.add("See Operator's statistics");
        ManagerMenu.add("See a student's statistics");

        BookSearchMenu.add("Title ");
        BookSearchMenu.add("Author ");
        BookSearchMenu.add("Publication Year ");

    }

    public void showStartMenu() {
        show(StartMenu);
    }

    public Integer getStartMenuSize() {
        return StartMenu.size();
    }

    public void showStudentMenu() {
        show(StudentMenu);
        showGoBack();
        System.out.println("-----------");
    }

    public Integer getStudentMenuSize() {
        return StudentMenu.size();
    }

    public void showOpMenu() {
        show(OpMenu);
        showGoBack();
        System.out.println("-----------");
    }

    public Integer getOpMenuSize() {
        return OpMenu.size();
    }


    public void showManagerMenu() {
        show(ManagerMenu);
        showGoBack();
        System.out.println("-----------");
    }

    public Integer getManagerMenuSize() {
        return ManagerMenu.size();
    }

    public void showGuestMenu() {
        show(GuestMenu);
        showGoBack();
        System.out.println("-----------");
    }

    public Integer getGuestMenuSize() {
        return GuestMenu.size();
    }

    public void showBookSearchMenu() {
        show(BookSearchMenu);
    }

    public Integer getBookSearchMenuSize() {
        return BookSearchMenu.size();
    }

    public void showGoBack() {
        System.out.println("0. Logout");
    }

    private void show(List<String> currentMenu) {
        for (int i = 0; i < currentMenu.size(); i++) {
            System.out.println(i + 1 + ". " + currentMenu.get(i));
        }
    }
}
