package ap.exercises.librarysystem2.repository;

import ap.exercises.librarysystem2.model.Student;

public class StudentService {

    public void ActivateStudent(Student student) {
        try {
            student.setStatus(true);
        } catch(Exception e){
            System.out.println("Student not found");
        }
    }

    public void DeactivateStudent(Student student) {
        try {
            student.setStatus(false);
        } catch(Exception e){
            System.out.println("Student not found");
        }
    }
}
