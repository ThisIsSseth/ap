package ap.exercises.MidTermLib.LM.LibManTools;

import ap.exercises.MidTermLib.LM.Members.Member;
import ap.exercises.MidTermLib.LM.Members.Student;

import java.util.List;

class SignIn {


    boolean passwordCheck(int password, Member member) {
        return member.passwordCheck(password);
    }

    boolean passwordCheck(int password, List<Member> memberList) {
        for (Member member : memberList) {
            if (member.passwordCheck(password)) {
                return true;
            }
        }
        return false;
    }

    /**Checks whether a student with that id has that password or not
     * */
    boolean studentPasswordCheck(int password, int id, List<Member> memberList) {
        for (Member member : memberList) {
            if (member instanceof Student) {
                Student student = (Student) member;
                if (student.getId() == id) {
                    return passwordCheck(password, student);
                }
            }
        }
        return false;
    }

    public void addStudent(Student student, List<Member> studentList) {
        if( studentList.contains(student)){}
        else{
            studentList.add(student);
        }
    }
}
