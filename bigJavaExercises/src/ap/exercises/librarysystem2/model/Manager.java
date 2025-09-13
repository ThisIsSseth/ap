package ap.exercises.librarysystem2.model;

public class Manager extends Member {
    private String education;

    /**
     *  @param id is a 5 digits number*/
    public Manager(String firstName, String lastName, int id, int pw, String education) {
        super(firstName, lastName, id, pw);
        this.education = education;
    }

    public String getEducation() {
        return education;
    }


}
