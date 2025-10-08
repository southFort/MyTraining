package w2d2;

import javax.tools.*;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Конструктор для компиляции файла, загрузке и запуску метода
 */
public class CompileJavaFile {
    private String inputFile;

    /**
     * Конструктор класса
     * @param inputFile исходный *.java файл. Плученный на предыдущем этапе
     */
    public CompileJavaFile(String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * Метод компиляции *.java файла в бинарный *.class
     * 1. Определяем директорию куда сохранить результирующий файл
     * 2. ToolProvider.getSystemJavaCompiler() - вызываем системный компилятор
     * 3. Создаем сущность для диагностики выполнения
     * 4. Создаем fileManager и запускаем компиляцию
     */
    public void compileJavaFile() throws Exception {
        Class<?> clazz = Starter.class;
        URL location = clazz.getProtectionDomain().getCodeSource().getLocation();
        String outputDirPath = location.getPath().toString();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            System.err.println("Compiler is unavailable");
            return;
        }

        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<>();

        try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(
                diagnosticCollector, Locale.ENGLISH, null)) {

            File[] javaFile = new File[]{new File(inputFile)};
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(List.of(javaFile));

            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(new File(outputDirPath)));

            boolean success = compiler.getTask(
                    null, fileManager, diagnosticCollector, null, null, compilationUnits).call();

            if (!success) {
                for (Diagnostic<?> diagnostic : diagnosticCollector.getDiagnostics()) {
                    System.out.format("Compilation error %s:%n%s%n",
                            diagnostic.getKind(),
                            diagnostic.getMessage(Locale.ENGLISH));
                }
            } else {
                System.out.println("File compiled successfully");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Загрузка объекта и запуск метода doWork
     */
    public void runDoWork() throws Exception {
        Class<?> someClass = loadCompiledClass("w2d2.SomeClass");
        Object instance = someClass.getDeclaredConstructor().newInstance();
        someClass.getMethod("doWork").invoke(instance);
    }

    /**
     * Загрузка класса
     * @param className
     */
    private Class<?> loadCompiledClass(String className) throws Exception {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        return loader.loadClass(className);
    }
}
