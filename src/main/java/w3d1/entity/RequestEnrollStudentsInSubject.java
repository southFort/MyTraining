package w3d1.entity;

import java.util.List;

public class RequestEnrollStudentsInSubject {
    private List<Integer> studentIds;
    private int subjectId;

    public RequestEnrollStudentsInSubject(List<Integer> studentIds, int subjectId) {
        this.studentIds = studentIds;
        this.subjectId = subjectId;
    }

    public List<Integer> getStudentIds() {
        return studentIds;
    }

    public int getSubjectId() {
        return subjectId;
    }
}
