package ap.exercises.librarysystem2.repository;

import ap.exercises.librarysystem2.model.Library;
import ap.exercises.librarysystem2.model.Student;

import java.util.Map;

class StudentCollection {
    private Library library;
    private Map<Integer, Student> studentMap;

    StudentCollection(Library library) {
        this.library = library;
        this.studentMap = library.getStudentMap();
    }

    public Student getStudent(int ID) {
        return this.studentMap.get(ID);
    }




}
