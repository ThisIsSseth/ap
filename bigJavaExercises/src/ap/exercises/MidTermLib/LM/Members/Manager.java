package ap.exercises.MidTermLib.LM.Members;

public class Manager extends Member {
    private String education;

    /**
     *  @param id a number between 10000 and 99999*/
    public Manager(String firstName, String lastName, int id, String education) {
        super(firstName, lastName, id, 9999);
        this.education = education;
    }

    @Override
    public String toString() {
        return super.toString() + this.education + regex;
    }

    public String getEducation() {
        return education;
    }


}
