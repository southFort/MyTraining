package w1d4;


import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException {
        String path = "/home/maksim/tmp";
        int coutnFile = 3;
        int sizeFileMb = 1;
        String[] words = TextFactory.wordsLibrary();
        int probability = 50;

        TextFileGenerator textFileGenerator = new TextFileGenerator
                (path, coutnFile, sizeFileMb, words, probability);

        textFileGenerator.getFile();
        System.out.println("Completed successfully");
    }
}
