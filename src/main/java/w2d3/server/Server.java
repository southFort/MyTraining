package w2d3.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 55238;
    private static final Set<User> users = new HashSet<User>();

    /**
     * Метод запуска сервера. При появлении нового запрос создает сущность user,
     * добавляет объект в коллекцию users и запускает новый поток для пользователя
     */
    public static void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT);
            while (true) {
                Socket userSocket = serverSocket.accept();
                System.out.println("[INFO] New connection: " + userSocket);
                User user = new User(userSocket);
                users.add(user);
                new Thread(user).start();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e);
        }
    }

    /**
     * Метод для отправки сообщений все активным пользователям в чате, кроме отправителя
     * @param message - сообщение для рассылки
     * @param sender - отправитель сообщения
     */
    public static void broadcast (String message, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.sendMessage(message);
            }
        }
    }

    /**
     * Метод удаления пользователя из списка активных и закрытие его сокета
     * @param user - пользователь которого удаляем
     */
    public static void removeUser (User user) {
        users.remove(user);
        try {
            user.socket.close();
        } catch (IOException e) {
            System.err.println("Client socket closing error: " + e);
        }
    }
}
