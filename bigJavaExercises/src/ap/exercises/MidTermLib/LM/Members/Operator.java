package ap.exercises.MidTermLib.LM.Members;

public class Operator extends Member {

    /**
     * @param oPId a number between 10000 and 99999
     */
    public Operator(String firstName, String lastName, int oPId) {
        super(firstName, lastName, oPId, 1000);
    }

    @Override
    public String toString() {
        return super.toString() + this.getId() + regex;
    }

}