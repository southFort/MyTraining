package w3d1.entity;

import java.util.List;

public class RequestEnrollStudentInSubjects {
    private int studentId;
    private List<Subject> subjects;

    public RequestEnrollStudentInSubjects(int studentId, List<Subject> subjects) {
        this.studentId = studentId;
        this.subjects = subjects;
    }

    public int getStudentId() {
        return studentId;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
}
