package w2d2;

import java.io.*;

public class Creator {
    /**
     * Метод создания *java файла. Основная конструкция файла задается статически, метод doWork
     * заполняем построчно с клавиатуры
     * Файл сохраняется в пакете программы
     * Пустая строка признак завершения ввода
     */
    public void creatorCode(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer=new BufferedWriter(new FileWriter(file))){

            String readedLine;

            writer.write("package w2d2;" + System.lineSeparator());
            writer.write("public class SomeClass implements Worker {" + System.lineSeparator());
            writer.write("\t@Override" + System.lineSeparator());
            writer.write("\tpublic void doWork() {" + System.lineSeparator());

            System.out.println("Enter the method code (empty line is end):");

            while (!(readedLine = reader.readLine()).isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append("\t\t");
                sb.append(readedLine);
                sb.append(System.lineSeparator());
                writer.write(sb.toString());
            }

            writer.write("\t}" + System.lineSeparator());
            writer.write("}");
            System.out.println("File has been created");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

}
