package ap.exercises.MidTermLib.LM.Members;

public class Manager extends Member {
    private String education;

    public Manager(String firstName, String lastName, int id, String education) {
        super(firstName, lastName, id, 9999);
        this.education = education;
    }

    @Override
    public String toString() {
        return super.toString() + this.education + "*";
    }

    public String getEducation() {
        return education;
    }


}
