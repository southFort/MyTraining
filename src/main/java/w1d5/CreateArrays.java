package w1d5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateArrays {
    private final String FILE_WORDS = "/home/maksim/tmp/w1d5/library.txt";
    private final String DIR_SOURSE_SMALL = "/home/maksim/tmp/w1d5/small";
    private final String DIR_SOURSE_VIM = "/home/maksim/tmp/w1d5/vim";
    private final String DIR_SOURSE_BIG = "/home/maksim/tmp/w1d5/big";

    /**
     * Метод возвращает массив слов для поиска
     *
     * @return
     */
    public String[] libraryWords() {
        List<String> wordsList = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_WORDS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordsList.add(line.toLowerCase().trim());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        String[] library = new String[wordsList.size()];
        for (int i = 0; i < wordsList.size(); i++) {
            library[i] = wordsList.get(i);
        }
        System.out.println("libraryWords done");
        return library;
    }

    /**
     * Метод возвращающий массив с ресурсами, пока реализовано, что перед запуском надо выбрать
     * с чем будм работать с 2000 маленьких файлов (DIR_SOURSE_SMALL) или несколькими большими
     * (DIR_SOURSE_BIG) в перспективе пересмотреть выбор на этапе старта программыэ
     *
     * @return
     */
    public String[] sourcesFile() {
        List<String> sourcesList = new ArrayList<String>();
        File directory = new File(DIR_SOURSE_VIM);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                sourcesList.add(file.toString());
            }
        }

        String[] sources = new String[sourcesList.size()];
        for (int i = 0; i < sourcesList.size(); i++) {
            sources[i] = sourcesList.get(i);
        }
        System.out.println("sourcesFile done");
        return sources;
    }
}
