package w3d1;

import java.util.List;

/**
 * Интерфейс по методам студентов
 */
public interface StudentDAO {
    List<Student> getAllStudent();
    List<Subject> getSubjectsByStudent(int studentId);
    void enrollStudentInSubject(int studenId, int subjectId);
    void excludeStudentFromSubject(int studentId, int subjectId);
}
