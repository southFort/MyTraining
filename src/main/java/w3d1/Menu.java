package w3d1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Метод для интерактивной работы с базой данных и реализованным функционалом
 */
public class Menu {
    private ParameterReader parameterReader = new ParameterReader(System.in);

    /**
     * Старт основной программы соединение с БД создание двух сущностей
     * для работы со студентами и с предметами
     */
    public void start() throws SQLException, IOException {
        ConnectionDB connectionDB = new ConnectionDB();
        Connection conn = connectionDB.getConnection();

        StudentDAO studentDAO = new StudentDAOImpl(conn);
        SubjectDAO subjectDAO = new SubjectDAOImpl(conn);

        firstMenu(studentDAO, subjectDAO);

        conn.close();
        parameterReader.close();
    }

    /**
     * Главное (первое) меню
     */
    private void firstMenu(StudentDAO studentDAO, SubjectDAO subjectDAO) throws IOException, SQLException {
        while (true) {
            System.out.println("\nГлавное меню:");
            System.out.println("1. Студенты");
            System.out.println("2. Предметы");
            System.out.println("0. Выход");
            int i = parameterReader.readerInt();
            switch (i) {
                case (1):
                    studentMenu(studentDAO, subjectDAO);
                    break;
                case (2):
                    subjectMenu(studentDAO, subjectDAO);
                    break;
                case (0):
                    return;
                default:
                    System.out.println("Нет выбранного пункта");
            }
        }
    }

    /**
     * Подменю для работы со студентами
     */
    private void studentMenu(StudentDAO studentDAO, SubjectDAO subjectDAO) throws IOException, SQLException {
        while (true) {
            System.out.println("\nСтуденты. Выберите операцию:");
            System.out.println("1. Список всех студентов");
            System.out.println("2. Найти все предметы студента");
            System.out.println("3. Записать студента на предмет");
            System.out.println("4. Исключить студента с предмета");
            System.out.println("0. Назад");
            int i = parameterReader.readerInt();
            switch (i) {
                case (1):
                    System.out.println("Список студентов:");
                    for (Student st : studentDAO.getAllStudent()) {
                        System.out.println(st);
                    }
                    break;
                case (2):
                    System.out.print("Введи id студента: ");
                    int studentId = parameterReader.readerInt();
                    System.out.println("Студент " + studentId + " записан на предметы:");
                    for (Subject sb : studentDAO.getSubjectsByStudent(studentId)) {
                        System.out.println(sb);
                    }
                    break;
                case (3):
                    System.out.println("Записываем студента на предмет");
                    System.out.print("Введи id студента: ");
                    int studentIdEnroll = parameterReader.readerInt();
                    System.out.print("Введи id предмета: ");
                    int subjectIdEnroll = parameterReader.readerInt();
                    studentDAO.enrollStudentInSubject(studentIdEnroll, subjectIdEnroll);
                    break;
                case (4):
                    System.out.println("Исключаем студента с курса");
                    System.out.print("Введи id студента: ");
                    int studentIdExclude = parameterReader.readerInt();
                    System.out.print("Введи id предмета: ");
                    int subjectIdExclude = parameterReader.readerInt();
                    studentDAO.excludeStudentFromSubject(studentIdExclude, subjectIdExclude);
                    break;
                case (0):
                    return;
                default:
                    System.out.println("Нет выбранного пункта");
            }
        }
    }

    /**
     * Подменю для работы с предметами
     */
    private void subjectMenu(StudentDAO studentDAO, SubjectDAO subjectDAO) throws IOException, SQLException {
        while (true) {
            System.out.println("\nПредметы. Выберите операцию:");
            System.out.println("1. Список всех предметов");
            System.out.println("2. Найти всех студентов на предмете");
            System.out.println("3. Добавить новый предмет");
            System.out.println("4. Удалить предмет");
            System.out.println("0. Назад");
            int i = parameterReader.readerInt();
            switch (i) {
                case (1):
                    System.out.println("Список предметов:");
                    for (Subject subj : subjectDAO.getAllSubjects()) {
                        System.out.println(subj);
                    }
                    break;
                case (2):
                    System.out.println("Ищем студентов");
                    System.out.print("Введи id предмета: ");
                    int subjectId = parameterReader.readerInt();
                    System.out.println("На предмет " + subjectId + " записаны студенты:");
                    for (Student st : subjectDAO.getStudentsBySubject(subjectId)) {
                        System.out.println(st);
                    }
                    break;
                case (3):
                    System.out.println("Добавляем новый предмет");
                    System.out.print("Введи название предмета: ");
                    String subjectAdd = parameterReader.readerStr();
                    subjectDAO.addSubject(subjectAdd);
                    break;
                case (4):
                    System.out.println("Исключаем предмет из программы");
                    System.out.print("Введи id предмета: ");
                    int subjectIdDelete = parameterReader.readerInt();
                    subjectDAO.deleteSubjects(subjectIdDelete);
                    break;
                case (0):
                    return;
                default:
                    System.out.println("Нет выбранного пункта");
            }
        }
    }
}
