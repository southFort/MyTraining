package w3d1.dao;

import w3d1.entity.Student;
import w3d1.entity.Subject;

import java.util.List;

/**
 * Интерфейс по методам студентов
 */
public interface StudentDAO {
    List<Student> getAllStudents();
    List<Subject> getSubjectsByStudent(int studentId);
    void excludeStudentFromSubject(int studentId, int subjectId);
}
