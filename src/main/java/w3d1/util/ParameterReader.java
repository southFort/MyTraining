package w3d1.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Класс для считывания параметров введенных с клавиатуры
 */
public class ParameterReader {
    private BufferedReader br;

    public ParameterReader(InputStream inputStream) {
        this.br = new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * Считывание числа
     */
    public int readerInt() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    /**
     * Считывание строки, вызываем когда вводим строковое значение, напр-р название предмета
     */
    public String readerStr() throws IOException {
        return br.readLine();
    }

    /**
     * Метод закрытия потока
     */
    public void close() throws IOException {
        if (br != null) {
            br.close();
        }
    }
}
