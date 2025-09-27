package w1d4;

import java.io.*;

public class TextFileGenerator {

    /**
     * Генерация файла. В массив байт заносим сгенерированный абзац. Если абзац
     * меньше заданного размера файла, то переносим его целиком. Если абзац больше,
     * чем размер файла, переносим сколько влазит
     *
     * @param path - путь до каталога
     * @param n - количество файлов
     * @param size - размер файла (в задании конкретики нет, будем считать в мегабайтах)
     * @param words - массив слов
     * @param probability - вероятность вхождения слова
     */
    public static void getFile(String path,
                               int n,
                               int size,
                               String[] words,
                               int probability) throws IOException {
        for (int fileNum = 0; fileNum < n; fileNum++) {
            try(BufferedOutputStream writer = new BufferedOutputStream(
                    new FileOutputStream(path
                            + File.separator
                            + "file_"
                            + fileNum
                            + ".txt"))) {
                int sizeFile = size * 1024 * 1024;
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
