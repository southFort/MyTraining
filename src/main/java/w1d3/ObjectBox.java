package w1d3;

import java.util.ArrayList;
import java.util.List;

public class ObjectBox {
    private List<Object> objectList;

    /**
     * Класс ObjectBox, который будет хранить коллекцию Object
     */
    public ObjectBox() {
        this.objectList = new ArrayList<>();
    }

    /**
     * Метод addObject принимает аргумет типа Oblect и добавляет его в коллекцию класса
     * @param obj
     */
    public void addObject(Object obj) {
        objectList.add(obj);
    }

    /**
     * Методе deleteObject удаляет заданные элемент если он присутсвует в коллекции
     * @param obj
     */
    public void deleteObject(Object obj) {
        objectList.remove(obj);
    }

    /**
     * Метод dump выводит коллекцию класса в одну строку
     */
    public void dump() {
        String result = "";
        for (Object o : objectList) {
            result = result + o + " ";
        }
        System.out.println(result.trim());
    }
}
