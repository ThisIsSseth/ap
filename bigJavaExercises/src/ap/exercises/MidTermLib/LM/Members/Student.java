package ap.exercises.MidTermLib.LM.Members;

import java.time.LocalDate;

public class Student extends Member {

    private String major;
    private LocalDate signUpDate;

    /**
     * @param firstName First name
     * @param lastName  Last name
     * @param id        ID (make sure the id is unique)
     * @param major     Major
     */
    public Student(String firstName, String lastName, int id, String major, LocalDate signUpDate, int pw) {
        super(firstName, lastName, id, pw);
        this.major = major;
        this.signUpDate = signUpDate;
    }

    public Student(String firstName, String lastName, int id, String major, int pw) {
        this(firstName, lastName, id, major, LocalDate.now(), pw);
    }

    @Override
    public String toString() {
        return super.toString() + this.major + "*" + this.signUpDate + "*";
    }
}

