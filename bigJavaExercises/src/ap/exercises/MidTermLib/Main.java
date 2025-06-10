package ap.exercises.MidTermLib;

class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();

        System.out.println("Welcome!\nLoading data...");
        //check for lib files, in lib files there are info of the lib class and the path of its respective class files
        menu.loadMultiLibrariesData();
        //add all found libs to libraryNameList
        while (menu.LibraryMenu()) {
            while (menu.signInToLibrary()) {
                while (menu.userMenu()) {
                }
            }
        }

        //here I start managing the library but if gets too complicated i'll move it to a separate class
        //Nope... I need a separate class to manage a single library


    }
}