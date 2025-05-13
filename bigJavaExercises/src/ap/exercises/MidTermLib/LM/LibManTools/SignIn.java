package ap.exercises.MidTermLib.LM.LibManTools;

import ap.exercises.MidTermLib.LM.Members.Member;
import ap.exercises.MidTermLib.LM.Members.Operator;
import ap.exercises.MidTermLib.LM.Members.Student;

import java.util.Map;

class SignIn {


    boolean passwordCheck(int password, Member member) {
        return member.passwordCheck(password);
    }

    /**
     * Checks whether a student with that id has that password or not
     */
    boolean studentPasswordCheck(int password, int id, Map<Integer, Student> studentList) {
        if (studentList.containsKey(id)) {
            return passwordCheck(password, studentList.get(id));
        }
        return false;
    }

    public void addStudent(Student student, Map<Integer, Student> studentList) {
        if (!studentList.containsKey(student.getId())) {
            studentList.put(student.getId(), student);
        }
    }

    public boolean operatorPasswordCheck(int password, int id, Map<Integer, Operator> operatorList) {
        if (operatorList.containsKey(id)) {
            return passwordCheck(password, operatorList.get(id));
        }
        return false;
    }
}
