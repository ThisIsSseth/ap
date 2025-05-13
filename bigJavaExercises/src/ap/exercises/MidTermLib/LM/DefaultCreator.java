package ap.exercises.MidTermLib.LM;

import ap.exercises.MidTermLib.LM.Members.Manager;
import ap.exercises.MidTermLib.LM.Members.Member;
import ap.exercises.MidTermLib.LM.Members.Operator;

import java.util.ArrayList;
import java.util.List;

public class DefaultCreator {
    public Library defaultLibrary() {
        Library library = new Library("default Library");
        return library;
    }

    public List<Member> defaultMemberList() {
        List<Member> memberList = new ArrayList<Member>();
        memberList.add(new Manager("Manager", "---", 1, "???"));
        memberList.add(new Operator("OP1", "Op lastName", 2));
        memberList.add(new Operator("OP2", "OP lastName",3));
        return memberList;
    }
}
