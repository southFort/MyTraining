package w1d2;

public class Starter {

    /**
     * Проверяем работы задания
     * int lenArray - задает длинну массива
     */

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
