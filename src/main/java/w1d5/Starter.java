package w1d5;

import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException, InterruptedException {
        CreateArrays createArrays = new CreateArrays();
        String[] source = createArrays.sourcesFile();
        String[] words = createArrays.libraryWords();
        String res = "/home/maksim/tmp/w1d5/output.txt";

        OccurrencesImp occurrences = new OccurrencesImp();
        occurrences.getOccurrences(source, words, res);
    }
}
