package w2d2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkerClassLoader extends ClassLoader {
    /**
     * Переопределение метода findClass
     * Если найден загружаем, нет передаем поиск на уровень вверх родителю
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.equals("w2d2.WorkerImp")) {
            try {
                byte[] classByte = Files.readAllBytes(Paths.get("./src/main/java/w2d2/WorkerImp.class"));
                return defineClass(name, classByte, 0, classByte.length);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        return super.findClass(name);
    }
}
