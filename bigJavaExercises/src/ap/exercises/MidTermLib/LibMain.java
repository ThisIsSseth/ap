package ap.exercises.MidTermLib;

import ap.exercises.MidTermLib.LM.Library;

import java.util.ArrayList;
import java.util.List;


public class LibMain {
    public static void main(String[] args) {
        LibMenu menu = new LibMenu();

        System.out.println("Welcome!\nLoading data...");
        //check for lib files, in lib files there are info of the lib class and the path of its respective class files
        //add all found libs to libraryList
        menu.LibraryMenu();
        menu.signInToLibrary();

        //here I start managing the library but if gets too complicated i'll move it to a separate class
        //Nope... I need a separate class to manage a single library


    }
}
