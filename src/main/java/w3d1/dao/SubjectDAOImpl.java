package w3d1.dao;

import w3d1.entity.Student;
import w3d1.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {
    private static final String GET_ALL_SUBJECTS =
            "SELECT * FROM subjects";
    private static final String GET_STUDENTS_BY_SUBJECT = """
            SELECT s.id, s.name, s.surname
            FROM students s
            JOIN student_subject ss ON s.id = ss.student_id
            WHERE ss.subject_id = ?
            """;
    private static final String ADD_SUBJECT =
            "INSERT INTO subjects (name) VALUES (?)";
    private static final String DELETE_SUBJECT =
            "DELETE FROM subjects WHERE id = ?";

    private Connection conn;

    public SubjectDAOImpl(Connection conn) {
        this.conn = conn;
    }

    /**
     * Получаем список всех предметов
     */
    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(GET_ALL_SUBJECTS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                subjects.add(new Subject(rs.getInt("id"),
                        rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return subjects;
    }

    /**
     * Получаем список студентов кто записан на предмет по id
     */
    @Override
    public List<Student> getStudentsBySubject(int subjectId) {
        List<Student> students = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(GET_STUDENTS_BY_SUBJECT)) {
            stmt.setInt(1, subjectId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    /**
     * Добавляем в БД новый предмет
     */
    @Override
    public void addSubject(String subject) {
        try (PreparedStatement stmt = conn.prepareStatement(ADD_SUBJECT)) {
            stmt.setString(1, subject);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Удаляем предмет из программы университета.
     * Автоматически все студенты исключаются с этого курса (удаляются все записи
     * в связной таблице)
     */
    @Override
    public void deleteSubjects(int subjectId) {
        try (PreparedStatement stmt = conn.prepareStatement(DELETE_SUBJECT)) {
            stmt.setInt(1, subjectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
