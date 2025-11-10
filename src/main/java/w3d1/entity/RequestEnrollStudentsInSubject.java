package w3d1.entity;

import java.util.List;

public class RequestEnrollStudentsInSubject {
    private List<Student> students;
    private int subjectId;

    public RequestEnrollStudentsInSubject(List<Student> students, int subjectId) {
        this.students = students;
        this.subjectId = subjectId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getSubjectId() {
        return subjectId;
    }
}
