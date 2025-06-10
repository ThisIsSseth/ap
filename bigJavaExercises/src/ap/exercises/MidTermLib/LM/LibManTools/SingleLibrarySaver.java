package ap.exercises.MidTermLib.LM.LibManTools;

import ap.exercises.MidTermLib.model.Book;
import ap.exercises.MidTermLib.LM.Borrow;
import ap.exercises.MidTermLib.model.Library;
import ap.exercises.MidTermLib.model.Member;
import ap.exercises.MidTermLib.model.Operator;
import ap.exercises.MidTermLib.model.Student;
import ap.exercises.MidTermLib.LM.Saver;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class SingleLibrarySaver {
    Saver studenMapSaver;
    Saver bookListSaver;
    Saver operatorMapSaver;
    Saver borrowerListSaver;
    Library library;

    public SingleLibrarySaver(Library library) {
        this.library = library;
        studenMapSaver = new Saver(this.library.getLibName() + "Students");
        operatorMapSaver = new Saver(this.library.getLibName() + "Operators");
        borrowerListSaver = new Saver(this.library.getLibName() + "Borrowers");
        bookListSaver = new Saver(this.library.getLibName() + "Books");
    }

    String studentListToStringFormater() {
        String studentListString = "";
        for (Member a : library.getStudentMap().values()) {
            studentListString += a + "\n";
        }
        return studentListString;
    }

    String bookListToStringFormater() {
        String bookListString = "";
        for (Book a : library.getBookList()) {
            bookListString += a + "\n";
        }
        return bookListString;
    }

    String operatorListToStringFormater() {
        String operatorListString = "";
        for (Operator a : library.getOperatorMap().values()) {
            operatorListString += a + "\n";
        }
        return operatorListString;
    }

    String borrowerListToStringFormater() {
        String borrowerListString = "";
        if (!library.getBorrowList().isEmpty()) {
            for (Borrow b : library.getBorrowList()) {
                borrowerListString += b + "\n";
            }
        }
        return borrowerListString;
    }

    public void saveData() {
        //saves data to file
        studenMapSaver.writeInto(studentListToStringFormater());
        bookListSaver.writeInto(bookListToStringFormater());
        operatorMapSaver.writeInto(operatorListToStringFormater());
        borrowerListSaver.writeInto(borrowerListToStringFormater());
        System.out.println("Saved successfully");
    }

    public void loadData() {
        //reads from file
        library.getStudentMap().clear();
        library.getBookList().clear();
        library.getOperatorMap().clear();
        library.getBorrowList().clear();

        //converts List of strings to list of objects and adds the objects directly to library lists
        loadAndConvertToBook(library.getBookList());
        loadAndConvertToOperator(library.getOperatorMap());
        loadAndConvertToStudent(library.getStudentMap());
        loadAndConvertToBorrower(library.getBorrowList(), library.getStudentMap(), library.getOperatorMap(), library.getBookList());

    }

    private void loadAndConvertToBook(List<Book> bookList) {
        String regex = new Book("", "", 0, 0).getRegex();
        List<String> bookStringList = bookListSaver.readLinetoList();
        if (bookStringList != null) {
            for (String a : bookStringList) {
                String[] b = a.split(regex);
                bookList.add(new Book(b[0], b[1], Integer.parseInt(b[2]), Integer.parseInt(b[3]), Integer.parseInt(b[4])));
            }
        }
    }

    private void loadAndConvertToOperator(Map<Integer, Operator> operatorMap) {
        List<String> operatorStringList = operatorMapSaver.readLinetoList();
        String regex = new Member("", "", 0, 0).getRegex();
        if(operatorStringList != null) {
            for (String a : operatorStringList) {
                String[] b = a.split(regex);
                operatorMap.put(Integer.parseInt(b[0]), new Operator(b[0], b[1], Integer.parseInt(b[2])));
            }
        }
    }

    private void loadAndConvertToStudent(Map<Integer, Student> studentMap) {
        List<String> studentStringList = studenMapSaver.readLinetoList();
        String regex = new Member("", "", 0, 0).getRegex();
        if (studentStringList != null) {
            for (String a : studentStringList) {
                String[] b = a.split(regex);
                studentMap.put(Integer.parseInt(b[2]), new Student(b[0], b[1], Integer.parseInt(b[2]), b[3], Integer.parseInt(b[4])));
            }
        }
    }

    private void loadAndConvertToBorrower(List<Borrow> borrowList, Map<Integer, Student> studentMap, Map<Integer, Operator> operatorMap, List<Book> bookList) {
        List<String> borrowerStringList = borrowerListSaver.readLinetoList();
        if (borrowerStringList != null) {
            for (String a : borrowerStringList) {
                String[] b = a.split("@");
                Borrow borrow = borrowCreator(b, studentMap, operatorMap, bookList);
                if(borrow != null) {
                    borrowList.add(borrow);
                }

            }
        }
    }

    private Borrow borrowCreator(String[] b, Map<Integer, Student> studentMap, Map<Integer, Operator> operatorMap, List<Book> bookList ){
        Borrow borrow = null;
        try{
            Book book = bookList.get(Integer.parseInt(b[0]));
            Student s = studentMap.get(Integer.parseInt(b[1]));
            borrow = new Borrow(s, book);
            try {
                Operator operator = operatorMap.get(Integer.parseInt(b[2]));
                LocalDate borrowDate = LocalDate.parse(b[3]);
                borrow.recreationOfBorrow(operator, borrowDate);
                try {
                    Operator operator2 = operatorMap.get(Integer.parseInt(b[4]));
                    LocalDate borrowDate2 = LocalDate.parse(b[5]);
                    borrow.recreationOfBorrow(operator, borrowDate, operator2, borrowDate2);
                } catch (IndexOutOfBoundsException e) {}
            } catch (IndexOutOfBoundsException e){}
        } catch (Exception e){}
        return borrow;
    }
}