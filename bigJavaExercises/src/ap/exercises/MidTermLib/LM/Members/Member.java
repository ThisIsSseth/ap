package ap.exercises.MidTermLib.LM.Members;


/**Includes {students, operators, manager}
 * */
public class Member {
    private String firstName;
    private String lastName;
    private int id;
    private int pw;

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
}
