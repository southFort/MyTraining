package w2d3.server;

import java.io.*;
import java.net.Socket;

/**
 * Класс управления пользователями на стороне сервера. Активируется когда
 * подключается новый пользователь. Каждому пользователю запускается
 * новый поток
 */
public class User implements Runnable {
    private String name;
    Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * Конструктор класса, при создании объекта подается текущий сокет
     * по которому подключился пользователь
     * @param socket
     */
    public User(Socket socket) {
        this.socket = socket;
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter (new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        } catch (IOException e) {
            System.err.println("Error creating client" + e.getMessage());
        }
    }

    /**
     * Нить каждого пользователя. Начинается с авторизации пользователя, если
     * пусто, то присваивается имя Аноним.
     * При каждом получении сообщения от пользователя проверяет если это слово quit,
     * то завершает поток, или рассылает сообщения всем остальным пользователям
     */
    @Override
    public void run() {
        try {
            this.out.println("[AUTH] Enter your name: ");
            this.out.flush();
            this.name = this.in.readLine();
            if (name == null || name.trim().isEmpty()) {
                name = "Anonymous";
            }
            System.out.println("[INFO] Client connected: " + name);
            Server.broadcast("[INFO] " + name + " joined the chat.", this);

            String message;
            while((message = in.readLine()) != null) {
                if ("quit".equalsIgnoreCase(message.trim())) {
                    break;
                }
                Server.broadcast("[" + name + "]: " + message, this);
                System.out.println("[" + name + "]: " + message);
            }
        } catch (IOException e) {
            System.err.println("Error client processing " + name + ": " + e.getMessage());
        } finally {
            Server.broadcast(name + " left the chat.", this);
            Server.removeUser(this);
            System.out.println(name + " left the chat.");
        }
    }

    /**
     * Метод отправки сообщения пользователю
     * @param message
     */
    public void sendMessage(String message) {
        out.println(message);
    }
}
