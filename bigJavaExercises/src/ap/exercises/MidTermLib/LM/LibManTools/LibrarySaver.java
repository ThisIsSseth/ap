package ap.exercises.MidTermLib.LM.LibManTools;

import ap.exercises.MidTermLib.LM.Library;
import ap.exercises.MidTermLib.LM.Members.Member;
import ap.exercises.MidTermLib.LM.Members.Operator;
import ap.exercises.MidTermLib.LM.Saver;

public class LibrarySaver {
    Saver studetnListSaver;
    Saver bookListSaver;
    Saver operatorListSaver;
    Saver borrowerListSaver;
    Library library;

    public LibrarySaver(Library library) {
        this.library = library;
        studetnListSaver = new Saver(this.library.getLibName() + "Students");
        bookListSaver = new Saver(this.library.getLibName() + "Books");
        operatorListSaver = new Saver(this.library.getLibName() + "Operators");
        borrowerListSaver = new Saver(this.library.getLibName() + "Borrowers");
    }

    public void save(Library library) {

    }

    public String readFromFile() {
        return "";
    }

    public String studentListFormater() {
        String studentListString = "";
        for (Member a : library.getStudentMap()){
            studentListString += a.toString() + "\n";
        }
        return studentListString;
    }

    public String bookListFormater() {
        String bookListString = "";
        for (int i = 0; i < library.getBookMap().size(); i++){
            bookListString += library.getBookMap().get(i) + "\n";
        }
        return bookListString;
    }

    public String operatorListFormater() {
        String operatorListString = "";
        for (Operator a : library.getOperatorMap()){

        }
    }
}
