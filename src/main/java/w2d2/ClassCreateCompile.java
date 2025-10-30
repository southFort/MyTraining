package w2d2;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;

public class ClassCreateCompile {
    /**
     * Метод создания *.java файла
     * @param className
     */
    public String createJavaFile(String className) {
        String fileName = "./src/main/java/w2d2/" + className + ".java";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            writer.write("package w2d2;" + System.lineSeparator());
            writer.write("public class " + className + " implements Worker {" + System.lineSeparator());
            writer.write("\t@Override" + System.lineSeparator());
            writer.write("\tpublic void doWork() {" + System.lineSeparator());

            writer.write(getMethod());

            writer.write("\t}" + System.lineSeparator() + "}");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return fileName;
    }

    /**
     * Метод компиляции *.java в бинарный *.class
     * @param fileName
     */
    public void javaCompile(String fileName) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, fileName);
        if (result == 0 ) {
            System.out.println("Compilation successful");
        } else {
            System.out.println("Compilation error");
        }
    }

    /**
     * Получаем тело метода из консоли, ввод построчно.
     * Пустая строка - конец ввода.
     *
     * @return строка с телом метода doWork
     */
    private String getMethod () {
        String readedLine;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!(readedLine = reader.readLine()).isEmpty()) {
                sb.append("\t\t").append(readedLine).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return (sb.toString());
    }
}
