package w2d2;


public class Starter {
    public static void main(String[] args) throws Exception {
        Worker worker = new WorkerImp();
        String className = "WorkerImp";

        ClassCreateCompile classCreateCompile = new ClassCreateCompile();
        String javaFile = classCreateCompile.createJavaFile(className);
        classCreateCompile.javaCompile(javaFile);

        WorkerClassLoader wCL = new WorkerClassLoader();
        Class<?> loadedClass = wCL.findClass("w2d2." + className);
        Object o = loadedClass.newInstance();
        worker = (Worker) o;

        worker.doWork();
    }
}
