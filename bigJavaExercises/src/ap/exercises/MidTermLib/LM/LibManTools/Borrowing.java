package ap.exercises.MidTermLib.LM.LibManTools;

import ap.exercises.MidTermLib.LM.Book;
import ap.exercises.MidTermLib.LM.Borrow;
import ap.exercises.MidTermLib.LM.Members.Operator;
import ap.exercises.MidTermLib.LM.Members.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Borrowing {

    Borrow borrowABook(Book book, Student student, Map<Integer, Operator> operatorMap) {
        return new Borrow(student, book, randomOperator(operatorMap));
    }

    Operator randomOperator(Map<Integer, Operator> operatorMap) {
        Random rand = new Random();
        List<Integer> list = new ArrayList<Integer>(operatorMap.keySet());
        int index = rand.nextInt(list.size());
        return operatorMap.get(list.get(index));
    }

    void returnABook(Borrow borrow, Map<Integer,Operator> operatorMap) {
        borrow.returnBook(randomOperator(operatorMap));
    }
}
