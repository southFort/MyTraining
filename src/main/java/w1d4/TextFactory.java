package w1d4;

import java.util.Random;

public class TextFactory {
    /**
     * Входные мараметры максимального значения
     * MAX_SIZE_WORDS_LIBRARY - максимальный размер библиотеки слов
     * MAX_LENGTH_WORD - максимальная длина слова
     * MAX_LENGTH_SENTENCE - максимальное число слов в предложении
     * MAX_LENGTH_PARAGRAPH - максималное количество предложений в абзаце
     */
    private static final int MAX_COUNT_WORDS_LIBRARY = 1000;
    private static final int MAX_LENGTH_WORD = 15;
    private static final int MAX_LENGTH_SENTENCE = 15;
    private static final int MAX_LENGTH_PARAGRAPH = 20;

    /**
     * gerParagraph - метод генерирует парграф количество предложений в параграфе
     * выбирается случайным образом от 1 до 20
     * @param wordsLib - массив слов
     * @param probability - вероятность вхождения слова в предложение
     */
    public static String genParagraph(String[] wordsLib, int probability) {
        StringBuilder paragraph = new StringBuilder();

        for (int i = 0; i < randomCount(MAX_LENGTH_PARAGRAPH); i++) {
            paragraph.append(genSentence(wordsLib, probability));
        }
        paragraph.append("\r\n");

        return paragraph.toString();
    }

    /**
     * genSentence - метод генерирует предложения, слова берет из входного
     * массива (сгенерированного ранее)
     * Первое слово в предложении пишем с заглавной буквы
     * В конце предложения случайный знак из метода selectPunctuation
     * Запятые ставим случайно если рандомно выпадет 1 в диапазоне от 1 до 3,
     * чем шире диапазон, тем реже будут встречаться запятые
     *
     * @param wordsLib - массив слов
     * @param probability - вероятность вхождения слова
     * @return
     */
    public static String genSentence(String[] wordsLib, int probability) {
        StringBuilder sent = new StringBuilder();
        int countWords = randomCount(MAX_LENGTH_SENTENCE);

        for (int i = 0; i < countWords; i++) {
            if (randomCount(probability) < probability) {
                if (i != 0) {
                    sent.append(wordsLib[randomCount(wordsLib.length) - 1]);
                    if (randomCount(3) == 1 && i < countWords - 2) {
                        sent.append(",");
                    }
                    sent.append(" ");
                } else {
                    String firstWord = wordsLib[randomCount(wordsLib.length) - 1];
                    sent.append(firstWord.substring(0, 1).toUpperCase() +
                            firstWord.substring(1) + " ");
                }
            }
        }
        String result = sent.toString().trim() + selectPunctuation() + " ";
        return result;
    }

    /**
     * selectPunctuation() - выбор знака окончания предложения
     */
    private static char selectPunctuation() {
        switch (randomCount(3)) {
            case 1:
                return '!';
            case 2:
                return '?';
            default:
                return '.';
        }
    }

    /**
     * wordsLibrary() - генерирует массив случайного размера от 1 до 1000 случайных слов
     */
    public static String[] wordsLibrary() {
        String[] words = new String[randomCount(MAX_COUNT_WORDS_LIBRARY)];
        for (int i = 0; i < words.length; i++) {
            words[i] = generateWord();
        }
        return words;
    }

    /**
     * generateWord() - генерирует слова случайно длины от 1 до 15 латинских символов
     */
    private static String generateWord() {
        int leftLimit = 96; // letter 'a'
        int rightLimit = 122; // letter 'z'
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < randomCount(MAX_LENGTH_WORD); i++) {
            int randomLimitedInt = leftLimit + randomCount(rightLimit - leftLimit);
            sb.append((char) randomLimitedInt);
        }
        return sb.toString();
    }

    /**
     * Метод выброса случайного числа
     * @param i - максимальное значение случайного числа
     * @return - случайное число от 1 до i
     */
    private static int randomCount(int i) {
        Random random = new Random();
        return random.nextInt(i) + 1;
    }
}
