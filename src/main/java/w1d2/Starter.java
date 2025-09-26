package w1d2;

/**
 * Реализовать любой алгоритм сортировки массива, содержащего Integer
 * с использованием циклов, ветвлений.
 * При необходимости применять исключения.
 *
 * переменная lenArray задает размер массива
 */

public class Starter {
    public static void main(String[] args) {
        int lenArray = 15;
        Integer[] array = Arrays.getArray(lenArray);

        System.out.println("Starting array");
        Arrays.printArray(array);

        System.out.println("Selection sort");
        Arrays.printArray(Arrays.sortSelection(array));

        System.out.println("Shaker Sort");
        Arrays.printArray(Arrays.sortShaker(array));
    }
}
