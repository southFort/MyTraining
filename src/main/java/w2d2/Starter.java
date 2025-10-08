package w2d2;

import java.io.File;


public class Starter {
    public static void main(String[] args) throws Exception {
        File file = new File(new File("src/main/java").getAbsolutePath()
                + File.separator
                + Starter.class.getPackage().getName()
                + File.separator
                + "SomeClass.java");

        Creator creator = new Creator();
        creator.creatorCode(file);

        CompileJavaFile comJF = new CompileJavaFile(file.toString());
        comJF.compileJavaFile();
        comJF.runDoWork();
    }
}
