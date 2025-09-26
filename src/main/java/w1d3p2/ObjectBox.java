package w1d3p2;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс ObjectBox, который будет хранить коллекцию Object
 *
 * Метод addObject принимает аргумет типа Oblect и добавляет его в коллекцию класса
 *
 * Методе deleteObject удаляет заданные элемент если он присутсвует в коллекции
 *
 * Метод dump выводит коллекцию класса в одну строку
 */

public class ObjectBox {
    private List<Object> objectList;

    public ObjectBox() {
        this.objectList = new ArrayList<>();
    }

    public void addObject(Object obj) {
        objectList.add(obj);
    }

    public void deleteObject(Object obj) {
        objectList.remove(obj);
    }

    public void dump() {
        String result = "";
        for (Object o : objectList) {
            result = result + o + " ";
        }
        System.out.println(result.trim());
    }
}
