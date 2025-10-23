package w2d4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemoryLeak {
    private static  final List<byte[]> memoryHog = new ArrayList<byte[]>();
    private static final int BLOCK_SIZE = 2_097_152; //Размер одного блока в байтах = 2МБ

    /**
     * Метод генерирующий каждую итерацию блоки по 2 МБ и записывающий их в массив static.
     * Основная задача переполнить кучу до предела, чтобы отловить ошибку переполнения памяти.
     * Каждый 10 итераций удаляем один блок, тем самым даем возможность GC очищать часть памяти.
     * Каждый 50 итераций показываем сколько блоков в памяти - сделано для визуализации.
     * Для наблюдения за процессом делаем слип на 50 мс
     */
    public void filler() {
        Random random = new Random();

        int repeatCount = 0;
        try {
            while (true) {
                repeatCount++;

                memoryHog.add(new byte[BLOCK_SIZE]);

                if (repeatCount % 10 == 0 && !memoryHog.isEmpty()) {
                    int indexRemove = random.nextInt(memoryHog.size());
                    memoryHog.remove(indexRemove);
                    System.out.println("Удален блок " + indexRemove + " Всего блоков: " + memoryHog.size());
                }

                if (repeatCount % 50 == 0) {
                    System.out.println("Итерация: " + repeatCount + " всего блоков " + memoryHog.size());
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    System.err.println("Ошибка сна: " + e);
                }
            }
        } catch (OutOfMemoryError e) {
            System.out.println(e.getStackTrace());
        }
    }
}
