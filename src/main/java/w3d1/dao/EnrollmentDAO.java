package w3d1.dao;

import w3d1.entity.RequestEnrollStudentInSubjects;
import w3d1.entity.RequestEnrollStudentsInSubject;

/**
 * Интерфейс работы с записями студентов на предметы
 */
public interface EnrollmentDAO {
    void enrollStudentInSubject(int studenId, int subjectId);
    void enrollStudentInSubjects(RequestEnrollStudentInSubjects request);
    void enrollStudentsInSubject(RequestEnrollStudentsInSubject request);
}
