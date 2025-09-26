package w1d3p2;

/**
 * Написать класс MathBox, реализующий следующий функционал:
 *
 * Часть 2
 *
 * Создать класс ObjectBox, который будет хранить коллекцию Object.
 *
 * У класса должен быть метод addObject, добавляющий объект в коллекцию.
 * У класса должен быть метод deleteObject, проверяющий наличие объекта
 * в коллекции и при наличии удаляющий его.
 * Должен быть метод dump, выводящий содержимое коллекции в строку.
 *
 * Класс MathBox необходимо доработать так, чтобы он работал не только с Integer,
 * но и с любым Number
 */

public class Starter {
    public static void main(String[] args) {
        Number[] inputArray = {48, 8.2, 1, 14.7, 10, 12, 12.4, 26, 41, 20, 7.5, 18, 31, 24, 31};
        MathBox box1 = new MathBox(inputArray);
        System.out.println("Коллекция box1: " + box1.toString());

        System.out.println(box1.splitter(2).toString());

        ObjectBox o1 = new ObjectBox();
        o1.addObject("Happy");
        o1.addObject(2026);
        o1.addObject("year");
        o1.addObject(3.1415);
        o1.addObject(false);
        o1.dump();
        o1.deleteObject("year");
        o1.dump();
    }
}
