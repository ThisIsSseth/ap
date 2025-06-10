package ap.exercises.ex3;

import static ap.exercises.ex3.Main_EX3_LM_1_3.studentId;

class Student {

    private String firstName;
    private String lastName;
    private int id;
    private String major;

    public Student(String firstName, String lastName, int id, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        studentId.add(id);
        this.major = major;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public int getId() {
        return id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }


}

