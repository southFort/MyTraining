package w1d3;

import java.util.ArrayList;
import java.util.TreeSet;

public class MathBox {
    private final TreeSet<Number> number;

    /**
     * Класс MathBox получает на входе массив содержащий элементы Number
     * Имеет, единственное поле коллекцию TreeSet инициализированную входным массивом.
     * Тип коллекции выбран согласно требованиям задания - значения в коллекции
     * должны быть уникальными и отсортированными.
     * @param inArray
     */
    public MathBox(Number[] inArray) {
        number = new TreeSet<Number>();
        try {
            for (Number n : inArray) {
                number.add(n.doubleValue());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * summator - возвращает сумму всех элементов коллекции
     * @return
     */
    public Number summator() {
        return number.stream()
                .mapToDouble(Number::doubleValue)
                .sum();
    }

    /**
     * splitter - каждый элемент поля number делим на входящий аргумент.
     * @param divider
     * @return
     */
    public ArrayList<Number> splitter (int divider) {
        if (divider == 0) throw new ArithmeticException("Деление на ноль");

        ArrayList<Number> resulSet = new ArrayList<>();
        for (Number item : number) {
            Number i = item.doubleValue() / divider;
            resulSet.add(i);
        }
        return resulSet;
    }

    /**
     * Переопределение hashCode - пересчитывается с использованием константы и hashCode поля number
     * @return
     */
    @Override
    public int hashCode() {
        final int p = 31;
        int result = 1;
        result = p * result + ((number == null) ? 0 : number.hashCode());
        return result;
    }

    /**
     * Переобпределение equals - объекты одинаковые, но они разные
     * @param obj   the reference object with which to compare.
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MathBox mathBox = (MathBox) obj;
        return number == mathBox.number;
    }

    /**
     * Переопределение toString - возвращает String все элементы коллекции (поле объекта)
     * @return
     */
    @Override
    public String toString() {
        String result = "[";
        for (Number item : number) {
            result = result + item.toString() + ' ';
        }
        result = result.trim() + "]";
        return result;
    }

    /**
     * delItem - если значение входящего аргумета присутствует в коллекции, он его удаляет
     * @param i
     */
    public void delItem (Integer i) {
        number.remove(i);
    }
}
