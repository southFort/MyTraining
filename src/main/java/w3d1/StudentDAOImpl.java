package w3d1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private Connection conn;

    public StudentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    /**
     * Получаем полный список всех студентов
     */
    @Override
    public List<Student> getAllStudent() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
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
        String sql = """
                SELECT s.id, s.name
                FROM subjects s
                JOIN student_subject ss ON s.id = ss.subject_id
                WHERE ss.student_id = ?
                """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            try(ResultSet rs = stmt.executeQuery()) {
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
     * Записываем студента на конкретный предмет. Вносим запись в связную таблицы
     * с id студента и предмета
     */
    @Override
    public void enrollStudentInSubject(int studentId, int subjectId) {
        String sql = "INSERT INTO student_subject (student_id, subject_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, subjectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Исключаем студента с предмета. Вводим id студента и с предмета, удаляется запись
     * из связной таблицы
     */
    @Override
    public void excludeStudentFromSubject(int studentId, int subjectId) {
        String sql = "DELETE FROM student_subject WHERE (student_id = ? AND subject_id = ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, subjectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
