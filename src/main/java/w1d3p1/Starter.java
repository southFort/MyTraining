package w1d3p1;

import w1d3p1.MathBox;

/**
 * Написать класс MathBox, реализующий следующий функционал:
 *
 *  Часть 1
 *
 * 1. Конструктор на вход получает массив Integer. Элементы не могут повторяться.
 * Элементы массива внутри конструктора раскладываются в подходящую коллекцию
 * (выбрать самостоятельно), являющуюся полем MathBox. Элементы должны отсортироваться
 *
 * 2. Существует метод summator, возвращающий сумму всех элементов коллекции
 *
 * 3. Существует метод splitter, выполняющий поочередное деление всех хранящихся
 * в объекте элементов на делитель, являющийся аргументом метода.
 * Метод возвращает коллекцию с результатами деления. Коллекция внутри mathbox
 * при этом не меняется
 *
 * 4. Необходимо правильно переопределить методы toString, hashCode, equals,
 * чтобы можно было использовать MathBox для вывода данных на экран и хранение
 * объектов этого класса в коллекциях (например, hashMap ). Выполнение контракта обязательно!
 *
 * 5. Есть метод, который получает на вход Integer и если такое значение есть
 * в коллекции, удаляет его.
 */

public class Starter {
    public static void main(String[] args) {
        Integer[] inputArray1 = {48, 8, 1, 14, 10, 12, 12, 26, 41, 20, 7, 18, 31, 24, 31};
        Integer[] inputArray2 = {38, 8, 1, 14, 10, 12, 12, 26, 41, 20, 7, 18, 31, 24, 31};
        MathBox box1 = new MathBox(inputArray1);
        MathBox box2 = new MathBox(inputArray2);
        System.out.println("Коллекция box1: " + box1.toString());
        System.out.println("Коллекция box2: " + box2.toString());
        System.out.println("Сумма всех элементов коллекции box1: " + box1.summator());
        int div = 2;
        System.out.println("Делим каждый элемент box1 на '" + div + "': "
                + box1.splitter(2));
        int i = 7;
        box1.delItem(i);
        System.out.println("Коллекция box1 после удаления '" + i + "': " + box1.toString());
        System.out.println("HashCode коллекции box1: " + box1.hashCode());
        System.out.println("HashCode коллекции box2: " + box2.hashCode());

        System.out.println("Сравниение box1 и box2: " + box1.equals(box2));
    }
}
