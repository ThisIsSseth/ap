package ap.exercises.librarysystem2.model;


/**
 * Includes {students, operators, manager}
 */
public class Member {
    private String firstName;
    private String lastName;
    private int id;
    private int pw;
    protected String regex = "@";

    /**
     * @param  id a number between 10000 and 99999
     * @param  pw a number between 1000 and 9999*/
    public Member(String firstName, String lastName, int id, int pw) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.pw = pw;
    }

    public String getRegex(){
        return regex;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean passwordCheck(int password) {
        return pw == password;
    }

    protected int getPw() {
        return pw;
    }


    /**
     * firstName@LastName@ID@PW@*/
    @Override
    public String toString() {
        return firstName + regex + lastName + regex + id + regex + pw + regex;
    }

}
