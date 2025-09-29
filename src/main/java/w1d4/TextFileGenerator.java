package w1d4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TextFileGenerator {
    private static final String FILE_EXTENSION = ".txt";

    private String path;
    private int n;
    private int size;
    private String[] words;
    private int probability;

    /**
     * Конструктор. Т.к. по заданию нет требования передавать поля объектов
     * опустим создание get'ов. При необхоимости можно добавить
     * path - путь к папке
     * n - количество файлов
     * size - размер файла
     * words - массив сгенерированных случайных слов
     * probability - вероятность попадания слова в следующее предложение
     */
    public TextFileGenerator(String path, int n, int size, String[] words, int probability) {
        this.path = path;
        this.n = n;
        this.size = size;
        this.words = words;
        this.probability = probability;
    }

    /**
     * Путь до папки с файлами
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Количество файлов
     * @param n
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * Размер файла в мегабайтах
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Словарь случайных слов
     * @param words
     */
    public void setWords(String[] words) {
        this.words = words;
    }

    /**
     * Вероятность попадания слова в следующее предложение
     * @param probability
     */
    public void setProbability(int probability) {
        this.probability = probability;
    }

    /**
     * Генерация файла. В массив байт заносим сгенерированный абзац. Если абзац
     * меньше заданного размера файла, то переносим его целиком. Если абзац больше,
     * чем размер файла, переносим сколько влазит
     */
    public void getFile() throws IOException {
        for (int fileNum = 0; fileNum < n; fileNum++) {
            try(BufferedOutputStream writer = new BufferedOutputStream(
                    new FileOutputStream(path
                            + File.separator
                            + "file_"
                            + fileNum
                            + FILE_EXTENSION))) {
                int sizeFile = size * 1024 * 1024;  //мегабайты переводим в байты
                while (sizeFile > 0) {
                    byte[] paragraph = TextFactory.genParagraph(words, probability).getBytes();
                    if (paragraph.length >= sizeFile) {
                        writer.write(paragraph, 0, sizeFile);
                        sizeFile = 0;
                    } else {
                        writer.write(paragraph, 0, paragraph.length);
                        sizeFile -= paragraph.length;
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
