package ap.exercises.librarysystem2.utils;

import ap.exercises.librarysystem2.model.*;
import com.google.gson.Gson;

public class DefaultLibraryCreator {

    public static Library create(){
        String LIBRARY_NAME = "DefaultLibrary";

        String MANAGER_FIRSTNAME = "DefaultManager";
        String MANAGER_LASTNAME = "Greg";
        Integer MANAGER_ID = 99999;
        Integer MANAGER_PASSWORD = 1234;
        String MANAGER_EDUCATION = "PhD Something";

        Manager DEFAULT_MANAGER = new Manager(MANAGER_FIRSTNAME,MANAGER_LASTNAME, MANAGER_ID, MANAGER_PASSWORD, MANAGER_EDUCATION);

        Library LIBRARY = new Library(LIBRARY_NAME, DEFAULT_MANAGER);

        // ----Students
        Student DEFAULT_STUDENT01 = new Student("FN01", "LN01", 77777, "CS", 7777 );
        Student DEFAULT_STUDENT02 = new Student ("FN02", "LN02", 88888, "CS", 8888);

        LIBRARY.getStudentMap().put( DEFAULT_STUDENT01.getId(), DEFAULT_STUDENT01);
        LIBRARY.getStudentMap().put( DEFAULT_STUDENT02.getId(), DEFAULT_STUDENT02);


        // ---- Operators
        Operator DEFAULT_OP01 = new Operator("OPFN01", "OPLN01", 11111, 1111);
        Operator DEFAULT_OP02 = new Operator("OFNP02", "OPLN02", 22222, 2222);

        LIBRARY.getOperatorMap().put(DEFAULT_OP01.getId(), DEFAULT_OP01);
        LIBRARY.getOperatorMap().put(DEFAULT_OP02.getId(), DEFAULT_OP02);

        //----- Books
        Book DEFAULT_BOOK01 = new Book("B01", "Auth01", 70, 2000);
        Book DEFAULT_BOOK02 = new Book("B02", "Auth02", 80, 2000, 5);

        LIBRARY.getBookList().add(DEFAULT_BOOK01);
        LIBRARY.getBookList().add(DEFAULT_BOOK02);

        return LIBRARY;

    }


    public static void main(String[] args) {
        Library library = DefaultLibraryCreator.create();
        Gson gson = new Gson();
        System.out.println(gson.toJson(library));
    }
}


