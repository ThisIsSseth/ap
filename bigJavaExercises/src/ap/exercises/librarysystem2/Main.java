package ap.exercises.librarysystem2;

import ap.exercises.librarysystem2.model.Library;
import ap.exercises.librarysystem2.storage.FileInitializer;
import ap.exercises.librarysystem2.storage.JsonHandler;
import ap.exercises.librarysystem2.utils.InputReader;
import ap.exercises.librarysystem2.utils.LoginStatus;
import ap.exercises.librarysystem2.utils.View;


import java.io.File;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        InputReader in = new InputReader();
        Integer option = null;
        Controller ctrl = new Controller(new JsonHandler<Library>().load(new File(FileInitializer.getFileName()), Library.class));

        while (true) {
            if (ctrl.getLoginStatus() == LoginStatus.LOGGED_OUT) {
                view.menu.showStartMenu();
                option = in.readInt(1, view.menu.getStartMenuSize());
                ctrl.startMenuDirector(option);
            } else if (ctrl.getLoginStatus() == LoginStatus.STUDENT) {
                view.menu.showStudentMenu();
                option = in.readInt(0, view.menu.getStudentMenuSize());
                ctrl.studentMenuDirector(option);
            } else if (ctrl.getLoginStatus() == LoginStatus.OPERATOR) {
                view.menu.showOpMenu();
                option = in.readInt(0, view.menu.getOpMenuSize());
                ctrl.operatorMenuDirector(option);
            } else if (ctrl.getLoginStatus() == LoginStatus.MANAGER) {
                view.menu.showManagerMenu();
                option = in.readInt(0, view.menu.getManagerMenuSize());
                ctrl.managerMenuDirector(option);
            } else if (ctrl.getLoginStatus() == LoginStatus.GUEST) {
                view.menu.showGuestMenu();
                option = in.readInt(0, view.menu.getGuestMenuSize());
                ctrl.guestMenuDirector(option);
            }


        }
    }
}
