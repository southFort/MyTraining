package w1d5;

import java.io.IOException;

public interface Occurrences {
    void getOccurrences(String[] sources, String[] words, String res) throws IOException, InterruptedException;
}
