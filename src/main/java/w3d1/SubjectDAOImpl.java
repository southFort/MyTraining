package w3d1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {
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
        String sql = "SELECT * FROM subjects";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
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
        String sql = """
                SELECT s.id, s.name, s.surname
                FROM students s
                JOIN student_subject ss ON s.id = ss.student_id
                WHERE ss.subject_id = ?
                """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, subjectId);
            try(ResultSet rs = stmt.executeQuery()) {
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
        String sql = "INSERT INTO subjects (name) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        String sql = "DELETE FROM subjects WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, subjectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
