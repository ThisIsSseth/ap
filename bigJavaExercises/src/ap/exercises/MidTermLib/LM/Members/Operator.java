package ap.exercises.MidTermLib.LM.Members;

public class Operator extends Member {
    private int oPID;
    public Operator(String firstName, String lastName, int oPId) {
        super(firstName, lastName, oPId, 1000);
        this.oPID = oPId;
    }

    @Override
    public String toString() {
        return super.toString() + this.oPID + "*";
    }
}
