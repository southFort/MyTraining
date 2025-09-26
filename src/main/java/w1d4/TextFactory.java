package w1d4;

import java.util.Random;

public class TextFactory {
    private static final int MAX_SIZE_WORDS_LIBRARY = 100;
    private static final int MAX_LIGTH_WORD = 15;
    private static final int MAX_LIGTH_SENTENCE = 15;

    public static String genSentence(String[] wordsLib) {
        StringBuilder sent = new StringBuilder();

        String firstWord = wordsLib[randomCount(wordsLib.length) - 1];
        sent.append(firstWord.substring(0, 1).toUpperCase() +
                firstWord.substring(1));

        for (int i = 1; i < MAX_LIGTH_SENTENCE; i++) {
            sent.append(wordsLib[randomCount(wordsLib.length) - 1]);
            if (randomCount(3) == 1 && i < MAX_LIGTH_SENTENCE - 2) {
                sent.append(",");
            }
            sent.append(" ");
        }
        String result = sent.toString().trim() + selectPunctuation();
        return result;
    }

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

    public static String[] wordsLibrary() {
        String[] words = new String[randomCount(MAX_SIZE_WORDS_LIBRARY)];
        for (int i = 0; i < words.length; i++) {
            words[i] = generateWord();
        }
        return words;
    }

    private static String generateWord() {
        int leftLimit = 96; // letter 'a'
        int rightLimit = 122; // letter 'z'
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < randomCount(MAX_LIGTH_WORD); i++) {
            int randomLimitedInt = leftLimit + randomCount(rightLimit - leftLimit);
            sb.append((char) randomLimitedInt);
        }
        return sb.toString();
    }

    private static int randomCount(int i) {
        Random random = new Random();
        return random.nextInt(i) + 1;
    }
}
