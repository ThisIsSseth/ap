package ap.exercises.MidTermLib.LM.Members;


/**
 * Includes {students, operators, manager}
 */
public class Member {
    private String firstName;
    private String lastName;
    private int id;
    private int pw;
    protected char regex = '@';

    /**
     * @param  id a number between 10000 and 99999
     * @param  pw a number between 1000 and 9999*/
    public Member(String firstName, String lastName, int id, int pw) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.pw = pw;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public boolean passwordCheck(int password) {
        return pw == password;
    }

    protected int getPw() {
        return pw;
    }

    @Override
    public String toString() {
        return firstName + regex + lastName + regex + id + regex + pw + regex;
    }

}
