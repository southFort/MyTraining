package w1d3p1;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Класс MathBox получает на входе массив содержащий элементы Integer
 * Имеет единственное поле коллекцию TreeSet инициализированную входным массивом.
 * Тип коллекции выбран согласно требования задания, значения в коллекции должны быть
 * уникальные и отсортированны
 *
 * Реализованные методы класса MathBox
 * summator - возвращает сумму всех элементов коллекции
 *
 * splitter - каждый элемент поля number делим на число полученное входящим аргументом.
 * Применили обычное деление, с отбрасыванием дробной части
 *
 * Переопределили методы toString, hashCode, equals
 * toString - выводит все элементы коллекции (поля объекта)
 * hashCode - пересчитывается с использованием константы и hashCode поля number,
 * equals - объекты одинаковые, но они разные
 *
 * delItem - если значение входящего аргумета присутствует в коллекции, он его удаляет
 */

public class MathBox {
    private final TreeSet<Integer> number;

    public MathBox(Integer[] inArray) {
        number = new TreeSet<>();

        for (Integer num : inArray) {
            if (num != null)
                number.add(num);
        }
    }

    public int summator() {
        int sum = 0;
        for (Integer item : number) {
           sum = sum + item;
        }
        return sum;
    }

    public ArrayList<Integer> splitter (int divider) {
        ArrayList<Integer> resulSet = new ArrayList<>();
        for (Integer item : number) {
            Integer i = item / divider;
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
        for (Integer item : number) {
            result = result + item.toString() + ' ';
        }
        result = result.trim() + "]";
        return result;
    }

    public void delItem (Integer i) {
        number.remove(i);
    }
}
