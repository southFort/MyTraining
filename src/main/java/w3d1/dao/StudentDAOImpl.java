package w3d1.dao;

import w3d1.entity.Student;
import w3d1.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private static final String GET_ALL_STUDENTS =
            "SELECT * FROM students";
    private static final String GET_SUBJECTS_BY_STUDENT = """
            SELECT s.id, s.name
            FROM subjects s
            JOIN student_subject ss ON s.id = ss.subject_id
            WHERE ss.student_id = ?
            """;
    private static final String EXCLUDE_STUDENT_FROM_SUBJECT =
            "DELETE FROM student_subject WHERE (student_id = ? AND subject_id = ?)";

    private Connection conn;

    public StudentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    /**
     * Получаем полный список всех студентов
     */
    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(GET_ALL_STUDENTS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    /**
     * Получаем список предметов на которые записан студент по id
     */
    @Override
    public List<Subject> getSubjectsByStudent(int studentId) {
        List<Subject> subjects = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(GET_SUBJECTS_BY_STUDENT)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    subjects.add(new Subject(rs.getInt("id"), rs.getString("name")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return subjects;
    }

    /**
     * Исключаем студента с предмета. Вводим id студента и с предмета, удаляется запись
     * из связной таблицы
     */
    @Override
    public void excludeStudentFromSubject(int studentId, int subjectId) {
        try (PreparedStatement stmt = conn.prepareStatement(EXCLUDE_STUDENT_FROM_SUBJECT)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, subjectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
