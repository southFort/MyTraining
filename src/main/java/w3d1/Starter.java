package w3d1;

import w3d1.dao.*;
import w3d1.entity.RequestEnrollStudentInSubjects;
import w3d1.entity.RequestEnrollStudentsInSubject;
import w3d1.entity.Student;
import w3d1.entity.Subject;
import w3d1.util.ConnectionDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Стартовый метод, начинаем с запуска меню
 */
public class Starter {
    static void main() throws SQLException, IOException {
        ConnectionDB connectionDB = new ConnectionDB();
        Connection conn = connectionDB.getConnection();

        StudentDAO studentDAO = new StudentDAOImpl(conn);
        SubjectDAO subjectDAO = new SubjectDAOImpl(conn);
        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl(conn);

        Student student4 = new Student(4, "Елена", "Кузницова");
        Student student8 = new Student(8, "Мария", "Кузьмина");
        Student student9 = new Student(9, "Иван", "Соколов");
        Student student10 = new Student(10, "Татьяна", "Новикова");

        Subject subject1 = new Subject(1, "Математический анализ");
        Subject subject2 = new Subject(2, "Программирование на Java");

        RequestEnrollStudentInSubjects request1 = new RequestEnrollStudentInSubjects(11, List.of(subject1, subject2));
        RequestEnrollStudentsInSubject request2 = new RequestEnrollStudentsInSubject(List.of(student4, student8, student9, student10), 6);

        enrollmentDAO.enrollStudentInSubjects(request1);
        enrollmentDAO.enrollStudentsInSubject(request2);
        System.out.println("Запросы по массовой записи завершены");

        List<Student> allStudents = studentDAO.getAllStudents();
        System.out.println("\nСписок студентов:");
        for (Student st : allStudents) {
            System.out.println(st);
        }

        List<Subject> allSubjects = subjectDAO.getAllSubjects();
        System.out.println("\nСписок предметов:");
        for (Subject subj : allSubjects) {
            System.out.println(subj);
        }

        String name = "Елена";
        String surName = "Кузнецова";
        System.out.println("\nСтудент " + name + " " + surName + " записан на предметы:");
        for (Student st : allStudents) {
            if (st.getName().equals(name) && st.getSurname().equals(surName)) {
                for (Subject sb : studentDAO.getSubjectsByStudent(st.getId())) {
                    System.out.println(sb);
                }
            }
        }

        String subject = "ООП";
        System.out.println("\nЗаписываем студента " + name + " " + surName + " на " + subject);
        for (Student st : allStudents) {
            if (st.getName().equals(name) && st.getSurname().equals(surName)) {
                for (Subject sb : allSubjects) {
                    if (sb.getName().equals(subject)) {
                        enrollmentDAO.enrollStudentInSubject(st.getId(), sb.getId());
                    }
                }
            }
        }
        System.out.println("Запись завершена");

        System.out.println("\nПроверка:");
        for (Student st : allStudents) {
            if (st.getName().equals(name) && st.getSurname().equals(surName)) {
                for (Subject sb : studentDAO.getSubjectsByStudent(st.getId())) {
                    System.out.println(sb);
                }
            }
        }

        System.out.println("\nИсключаем студента " + name + " " + surName + " с " + subject);
        for (Student st : allStudents) {
            if (st.getName().equals(name) && st.getSurname().equals(surName)) {
                for (Subject sb : allSubjects) {
                    if (sb.getName().equals(subject)) {
                        studentDAO.excludeStudentFromSubject(st.getId(), sb.getId());
                    }
                    System.out.println(sb);
                }
            }
        }
        System.out.println("Исключение завершена");

        System.out.println("\nПроверка:");
        for (Student st : allStudents) {
            if (st.getName().equals(name) && st.getSurname().equals(surName)) {
                for (Subject sb : studentDAO.getSubjectsByStudent(st.getId())) {
                    System.out.println(sb);
                }
            }
        }

        String subjectSearch = "Теория вероятности";
        System.out.println("\nНа предмет '" + subjectSearch + "' записаны студенты:");
        for (Subject sb : allSubjects) {
            if (sb.getName().equals(subjectSearch)) {
                for (Student st : subjectDAO.getStudentsBySubject(sb.getId())) {
                    System.out.println(st);
                }
            }
        }

        String subjectAdd = "Компьютерная графика";
        subjectDAO.addSubject(subjectAdd);
        System.out.print("\nДобавлен новый предмет: '" + subjectAdd + "'\n");

        System.out.println("\nПроверка:");
        for (Subject subj : subjectDAO.getAllSubjects()) {
            System.out.println(subj);
        }

        String subjectNameDel = "Компьютерная графика";
        for (Subject sub : subjectDAO.getAllSubjects()) {
            if (subjectNameDel.equals(sub.getName())) {
                subjectDAO.deleteSubjects(sub.getId());
                System.out.print("\nПредмет: '" + subjectNameDel + "' удален из программы\n");
            }
        }

        System.out.println("\nПроверка:");
        for (Subject subj : subjectDAO.getAllSubjects()) {
            System.out.println(subj);
        }

        conn.close();
    }
}
