package ap.exercises.librarysystem2;

import ap.exercises.librarysystem2.utils.InputReader;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        InputReader in = new InputReader();
        Integer option = null;

        while(true) {
            menu.showStartMenu();
            option = in.readInt(1, menu.getStartMenuSize());

        }


    }
}
