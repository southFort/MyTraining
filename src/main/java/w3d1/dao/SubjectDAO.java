package w3d1.dao;

import w3d1.entity.Student;
import w3d1.entity.Subject;

import java.util.List;

/**
 * Интерфейс по методам предметов
 */
public interface SubjectDAO {
    List<Subject> getAllSubjects();
    List<Student> getStudentsBySubject(int subjectId);
    void addSubject(String subject);
    void deleteSubjects(int subjectId);
}
