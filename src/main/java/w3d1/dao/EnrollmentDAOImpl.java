package w3d1.dao;

import w3d1.entity.RequestEnrollStudentInSubjects;
import w3d1.entity.RequestEnrollStudentsInSubject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnrollmentDAOImpl implements EnrollmentDAO {
    private static final String ADD_STUDENT_SUBJECT = """
            INSERT INTO student_subject (student_id, subject_id)
            VALUES (?, ?)
            ON CONFLICT (student_id, subject_id) DO NOTHING
            """;

    private Connection conn;

    public EnrollmentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    /**
     * Метод записи студента на несколько предметов
     * предварительно создаем сущность, связки один студент и несколько предметом.
     * Добавление организовано через транзакцию, сначала создаем запросы по всему массиву
     * предметов, после разом создаем.
     * В случае ошибки, все откатываем
     * @param request
     */
    @Override
    public void enrollStudentInSubjects(RequestEnrollStudentInSubjects request) {
        try (PreparedStatement stmt = conn.prepareStatement(ADD_STUDENT_SUBJECT)) {
            conn.setAutoCommit(false);
            for (Integer subjectId : request.getSubjectIds()) {
                stmt.setInt(1, request.getStudentId());
                stmt.setInt(2, subjectId);
                stmt.addBatch();
            }
            stmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод записи группы студентов на предмет
     * предварительно создаем сущность, связки один предмет и несколько студентов.
     * Добавление организовано через транзакцию, сначала создаем запросы по всему массиву
     * предметов, после разом создаем.
     * В случае ошибки, все откатываем
     * @param request
     */
    @Override
    public void enrollStudentsInSubject(RequestEnrollStudentsInSubject request) {
        try (PreparedStatement stmt = conn.prepareStatement(ADD_STUDENT_SUBJECT)) {
            conn.setAutoCommit(false);
            for (Integer studentId : request.getStudentIds()) {
                stmt.setInt(1, studentId);
                stmt.setInt(2, request.getSubjectId());
                stmt.addBatch();
            }
            stmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
