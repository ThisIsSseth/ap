package ap.exercises.MidTermLib.LM.LibManTools;

import ap.exercises.MidTermLib.LM.Members.Member;

import java.util.List;

//keeps the list of members and manages logins
class MemberManager {

    public MemberManager() {
    }
    /**
     * returns true if add was successful
     * false otherwise
     * @param member Member
     * @param memberList a list of members
     */
    public boolean addMember(Member member,List<Member> memberList) {
        if (!isInIt(member,memberList)) {
            memberList.add(member);
            return true;
        }
        return false;
    }

    boolean isInIt(Member member, List<Member> memberList) {
        for (Member m : memberList){
            if (m.getId() == member.getId()){
                return true;
            }
        }
        return false;
    }

}
