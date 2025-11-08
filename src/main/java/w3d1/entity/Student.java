package w3d1.entity;

/**
 * Класс для студентов. Считывая студентов из БД, каждому создаем сущность
 */
public class Student {
    private int id;
    private String name;
    private String surname;

    public Student(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', surname='" + surname + "'}";
    }
}
