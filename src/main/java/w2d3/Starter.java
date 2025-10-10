package w2d3;

import w2d3.client.Client;
import w2d3.server.Server;

import java.util.Scanner;

public class Starter {

    /**
     * Старт программы начинается с предлоежения выбрать в каком режиме запускаемся
     * Клиент или Сервер
     * В первом случае запуститься модуль управления клиентской частью
     * Во втором будет запущен сервер
     */
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        int option = 0;

        while (option != 3) {
            System.out.println("Что запускаем?\n"
                    + "1. Клиент\n"
                    + "2. Сервер\n"
                    + "3. Выход\n");

            option = reader.nextInt();

            switch (option) {
                case 1:
                    Client.start();
                    return;
                case 2:
                    Server.start();
                    return;
                case 3:
                    return;
                default:
                    System.out.println("Неизвестные данные");
                    break;
            }
        }
    }
}
