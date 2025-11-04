package w3d1;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Стартовый метод, начинаем с запуска меню
 */
public class Starter {
    static void main() throws SQLException, IOException {
        Menu menu = new Menu();
        menu.start();
    }
}
