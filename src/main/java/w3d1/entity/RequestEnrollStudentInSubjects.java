package w3d1.entity;

import java.util.List;

public class RequestEnrollStudentInSubjects {
    private int studentId;
    private List<Integer> subjectIds;

    public RequestEnrollStudentInSubjects(int studentId, List<Integer> subjectIds) {
        this.studentId = studentId;
        this.subjectIds = subjectIds;
    }

    public int getStudentId() {
        return studentId;
    }

    public List<Integer> getSubjectIds() {
        return subjectIds;
    }
}
