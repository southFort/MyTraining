package w1d5;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class OccurrencesImp implements Occurrences {
    private static final int MAX_SOURCES = 2000;
    private static final int MAX_WORDS = 2000;

    /**
     * Основной метод по заданию. На старте проверяется, если хоть один из входных параметров пустой,
     * то выбрасываем исключение, так же проверяем лимит размеров массивов с ресурсами или со словами
     * поиска.
     * Очищаем выходной файл путем записи в него нулевого массива байт.
     * Массив со словами преобразовываем к нижнему регистру.
     * Экземпляр executor создаем с ограниченным пулом потоков, равному количеству физических и
     * виртуальных процессоров.
     * Очередь результатов - служит мостом для передачи предложений из потоков обработки ресурсов
     * в поток записи результатов. Поддерживая потокобезопасность - в моменте пишется только один ресурс.
     * Переменная hasError будет информировать другие потоки, если где-то произойдет ошибка.
     *
     * @param sources - массив ресурсов
     * @param words   - массив слов для поиска
     * @param res     - выходной файл с результатом
     * @throws IOException, InterruptedException
     */
    @Override
    public void getOccurrences(String[] sources, String[] words, String res) throws IOException, InterruptedException {
        if (sources == null || words == null || res == null) {
            throw new IllegalArgumentException("Аргумент не может быть пустым");
        }

        if (sources.length > MAX_SOURCES || words.length > MAX_WORDS) {
            throw new IllegalArgumentException("Превышен максимальный лимит ресурсов или слов");
        }

        Files.write(Paths.get(res), new byte[0]);
        System.out.println("File is cleared");

        int threadPoolSize = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

        BlockingQueue<String> resultsQueue = new LinkedBlockingDeque<String>();
        AtomicBoolean hasError = new AtomicBoolean(false);

        Thread writerThread = new Thread(() -> {
            try (PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(res), StandardCharsets.UTF_8))) {
                String line;
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        line = resultsQueue.poll(1, TimeUnit.SECONDS);
                        if (line != null) {
                            writer.println(line);
                            writer.flush();
                        } else if (executor.isTerminated()) {
                            break;
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
        writerThread.start();
        System.out.println("writerThread started");

        for (String source : sources) {
            Runnable task = () -> {
                try {
                    sentenceSplitterSearcher(source, words, resultsQueue);
                } catch (Exception e) {
                    System.err.println("Во время обработки ресурса " + source + ", возникла ошибка " + e.getMessage());
                    hasError.set(true);
                }
            };
            executor.submit(task);
        }

        executor.shutdown();

        if (hasError.get()) {
            throw new IOException("Ошибка на одном или больше ресурсах");
        }
    }

    /**
     * Метод делит входящий ресурс побайтно на предложения согласно условиям задачи.
     * После определения конца предложения вызывает метод по поиску вхождения поисковых слов в данном предложении
     * Если вхождение найдено, то записываем в очередь в поток, который собирает итоговый файл
     * Небольшое отступление от условий задачи, в результирующий файл добавляю к началу предложения
     * ресурс, откуда это предложение считано, При желании удалить из 160 строки
     *
     * @param source массив ресурсов: txt файлы, http или ftp ссылки
     * @param words массив искомых слов, которые нужно искать
     * @param resultQueue очередь для записи результатов в общий файл для всех потоков
     * @throws IOException
     * @throws InterruptedException
     */
    public void sentenceSplitterSearcher(String source, String[] words, BlockingQueue<String> resultQueue)
            throws IOException, InterruptedException {

        StringBuilder current = new StringBuilder();

        System.out.println(source + " started");

        BufferedReader reader;
        if (source.startsWith("http://") || source.startsWith("https://") || source.startsWith("ftp://")) {
            URL url = new URL(source);
            reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
        } else {
            reader = new BufferedReader(new FileReader(source));
        }

        String line;
        while ((line = reader.readLine()) != null) {
            int n = line.length();
            for (int i = 0; i < n; i++) {
                char ch = line.charAt(i);
                if (current.isEmpty()) {
                    if (Character.isUpperCase(ch)) {
                        current.append(ch);
                    } else {
                        continue;
                    }
                } else {
                    current.append(ch);
                }

                if (ch == '.' || ch == '!' || ch == '?' || ch == '…') {
                    if (ch == '.' && i + 2 < n && line.charAt(i + 1) == '.' && line.charAt(i + 2) == '.') {
                        current.append("..");
                        i += 2;
                    }

                    int j = i + 1;
                    while (j < n && isWhitespace(line.charAt(j))) {
                        j++;
                    }

                    boolean isEndOfSentence = false;
                    if (j >= n) {
                        isEndOfSentence = true;
                    } else if (Character.isUpperCase(line.charAt(j)) || line.charAt(j) == '-' || (int) line.charAt(j) == 8211) { //8211 - тире
                        isEndOfSentence = true;
                    }

                    if (isEndOfSentence && containsWord(current.toString().trim(), words)) {
                        resultQueue.put(source + ' ' + current.toString().trim());
                        current.setLength(0);
                    } else {
                        current.setLength(0);
                    }
                }
            }
        }
        reader.close();
        System.out.println(source + " ended");
    }

    /**
     * Если символ пробел, неразрывный пробел (код 160), перевод строки или табуляция, возвращаем истину
     * Метод помогает дойти до следующего непробельного символа
     */
    private boolean isWhitespace(char c) {
        return c == ' ' || (int) c == 160 || c == '\t' || c == '\n' || c == '\r';
    }

    /**
     * Проверка вхождения слов из массива слов в предложение. Чтобы искать четко заданные слова, а не подстроки,
     * Предварительно убираем все заглавные буквы и разносим предложение по словам в массик.
     * Истинна если вхождение есть, иначе ложь.
     *
     * @param sentence - проверяемое предложение
     * @param words    - массив проверяемых слов
     */
    private boolean containsWord(String sentence, String[] words) {
        String lowerSentence = sentence.toLowerCase();
        String[] wordsLowerSentence = lowerSentence.split(" ");
        for (String wordSentence : wordsLowerSentence) {
            for (String word : words) {
                if (wordSentence.equals(word)) {
                    return true;
                }
            }
        }
        return false;
    }
}
