package w1d4;


import java.io.IOException;
import java.nio.file.Path;

/**
 * Создать генератор текстовых файлов, работающий по следующим правилам:
 * <p>
 * 1. Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных
 * слов могут находиться запятые. +
 * <p>
 * 2. Слово состоит из 1<=n2<=15 латинских букв +
 * <p>
 * 3. Слова разделены одним пробелом +
 * <p>
 * 4. Предложение начинается с заглавной буквы +
 * <p>
 * 5. Предложение заканчивается (.|!|?)+" " +
 * <p>
 * 6. Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений. В конце
 * абзаца стоит разрыв строки и перенос каретки. +
 * <p>
 * 7. Есть массив слов 1<=n4<=1000. Есть вероятность probability вхождения одного
 * из слов этого массива в следующее предложение (1/probability).
 * <p>
 * Задача: написать метод void getFiles(String path, int n, int size, String[] words,
 * int probability), который создаст n файлов размером size в каталоге path.
 * Words - массив слов из п. 7, probability - вероятность из п. 7.
 *
 */

public class Starter {
    public static void main(String[] args) throws IOException {
        String path = "/home/maksim/tmp";
        int coutnFile = 3;
        int sizeFileMb = 1;
        String[] words = TextFactory.wordsLibrary();
        int probability = 50;

        TextFileGenerator.getFile(path, coutnFile, sizeFileMb, words, probability);
        System.out.println("Completed successfully");
    }
}
