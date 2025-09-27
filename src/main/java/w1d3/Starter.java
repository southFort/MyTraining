package w1d3;

/**
 *
 */

public class Starter {
    public static void main(String[] args) {
        Number[] inputArray = {48, 8.2, 1, 14.7, 10, 12, 12.4f, 26, 41, 20, 7.5, 18, 31, 24, 31};
        MathBox box1 = new MathBox(inputArray);
        System.out.println("Коллекция box1: " + box1.toString());
        System.out.println("Сумма всех элементов коллекции box1: " + box1.summator());

        System.out.println(box1.splitter(2).toString());

        ObjectBox o1 = new ObjectBox();
        o1.addObject("Happy");
        o1.addObject(2026);
        o1.addObject("year");
        o1.addObject(3.1415);
        o1.addObject(false);
        o1.dump();
        o1.deleteObject("year");
        o1.deleteObject("car");
        o1.dump();
    }
}
