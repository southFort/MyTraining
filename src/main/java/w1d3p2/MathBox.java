package w1d3p2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Класс MathBox получает на входе массив содержащий элементы Number
 * Имеет единственное поле коллекцию TreeSet инициализированную входным массивом.
 * Тип коллекции выбран согласно требования задания, значения в коллекции должны быть
 * уникальные и отсортированны
 *
 * Реализованные методы класса MathBox
 * summator - возвращает сумму всех элементов коллекции
 *
 * splitter - каждый элемент поля number делим на число полученное входящим аргументом.
 *
 * Переопределили методы toString, hashCode, equals
 * toString - выводит все элементы коллекции (поля объекта)
 * hashCode - пересчитывается с использованием константы и hashCode поля number,
 * equals - объекты одинаковые, но они разные
 *
 * delItem - если значение входящего аргумета присутствует в коллекции, он его удаляет
 */

public class MathBox {
    private final TreeSet<Number> number;

    public MathBox(Number[] inArray) {
        number = new TreeSet<>();
        try {
            for (Number n : inArray) {
                number.add(n.doubleValue());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    public Number summator() {
        return number.stream()
                .mapToDouble(Number::doubleValue)
                .sum();
    }

    public ArrayList<Number> splitter (int divider) {
        if (divider == 0) throw new ArithmeticException("Деление на ноль");
        ArrayList<Number> resulSet = new ArrayList<>();
        for (Number item : number) {
            Number i = item.doubleValue() / divider;
            resulSet.add(i);
        }
        return resulSet;
    }

    @Override
    public int hashCode() {
        final int p = 31;
        int result = 1;
        result = p * result + ((number == null) ? 0 : number.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MathBox mathBox = (MathBox) obj;
        return number == mathBox.number;
    }

    @Override
    public String toString() {
        String result = "[";
        for (Number item : number) {
            result = result + item.toString() + ' ';
        }
        result = result.trim() + "]";
        return result;
    }

    public void delItem (Integer i) {
        number.remove(i);
    }
}
