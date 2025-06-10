package ap.exercises.librarysystem2.model;

import java.time.LocalDate;

public class Student extends Member {

    private String major;
    private LocalDate signUpDate;

    /**
     * @param firstName First name
     * @param lastName  Last name
     * @param id        ID (make sure the id is unique) a number between 10000 and 99999
     * @param major     Major
     * @param pw a number between 1000 and 9999
     */
    public Student(String firstName, String lastName, int id, String major, LocalDate signUpDate, int pw) {
        super(firstName, lastName, id, pw);
        this.major = major;
        this.signUpDate = signUpDate;
    }

    public Student(String firstName, String lastName, int id, String major, int pw) {
        this(firstName, lastName, id, major, LocalDate.now(), pw);
    }

    /**
     * major@signUpDate@*/
    @Override
    public String toString() {
        return super.toString() + this.major + regex + this.signUpDate + regex;
    }
}

