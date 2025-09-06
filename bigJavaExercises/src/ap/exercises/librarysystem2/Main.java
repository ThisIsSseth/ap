package ap.exercises.librarysystem2;

import ap.exercises.librarysystem2.model.Library;
import ap.exercises.librarysystem2.storage.FileInitializer;
import ap.exercises.librarysystem2.storage.JsonHandler;
import ap.exercises.librarysystem2.utils.DefaultLibraryCreator;
import ap.exercises.librarysystem2.utils.InputReader;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        InputReader in = new InputReader();
        Integer option = null;
        Controller ctrl = new Controller(new JsonHandler<Library>().load(new File(FileInitializer.getFileName()), Library.class));

        while (true) {
            if (ctrl.loginStatus == Controller.LoginStatus.LOGGED_OUT) {
                menu.showStartMenu();
                option = in.readInt(1, menu.getStartMenuSize());
                ctrl.startMenuDirector(option);
            } else if (ctrl.loginStatus == Controller.LoginStatus.STUDENT) {
                menu.showStudentMenu();
                option = in.readInt(0, menu.getStudentMenuSize());
                ctrl.studentMenu(option);
            } else if (ctrl.loginStatus == Controller.LoginStatus.OPERATOR) {
                menu.showOpMenu();
                option = in.readInt(0, menu.getOpMenuSize());
                ctrl.operatorMenu(option);
            } else if (ctrl.loginStatus == Controller.LoginStatus.MANAGER) {
                menu.showManagerMenu();
                option = in.readInt(0, menu.getManagerMenuSize());
                ctrl.managerMenu(option);
            } else if (ctrl.loginStatus == Controller.LoginStatus.GUEST) {
                menu.showGuestMenu();
                option = in.readInt(0, menu.getGuestMenuSize());
                ctrl.guestMenu(option);
            }


        }
    }
}
