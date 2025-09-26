package w1d2;

import java.util.Random;

/**
 * метод getArray создает и возвращает массив размером lenArray, заполненный
 * случайными числами от 0 до lenArray * 3 (повтор не исключен)
 * <p>
 * метод sortSelection производит сортировку выбором переданного в него массива
 * <p>
 * метод sortShaker производит Шейкерную сортировку переданного в него массива
 * <p>
 * метод printArray принимает на входе массив содержащий Integer и
 * выводит его на экран в одну строку
 * <p>
 * метов swap принимает на входе массив и два индекса, меняет местами элементы
 * массива по входным индексам
 */

public class Arrays {
    public static Integer[] getArray(int lenArray) {
        if (lenArray == 0) {
            System.out.println("Попытка создани пустого массива");
            throw new NullPointerException();
        }

        Integer[] array = new Integer[lenArray];

        Random random = new Random();
        for (int i = 0; i < lenArray; i++) {
            array[i] = random.nextInt(lenArray * 3);
        }
        return array;
    }

    public static Integer[] sortSelection(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    swap(array, i, j);
                }
            }
        }
        return array;
    }

    public static Integer[] sortShaker(Integer[] array) {
        int begin = 0;
        int end = array.length - 1;

        while (begin < end) {
            for (int i = begin; i < end; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                }
            }
            end--;

            for (int j = end; j > begin + 1; j--) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                }
            }
            begin++;
        }
        return array;
    }

    public static void printArray(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println(arr[arr.length - 1]);
    }

    private static Integer[] swap(Integer[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        return array;
    }
}
