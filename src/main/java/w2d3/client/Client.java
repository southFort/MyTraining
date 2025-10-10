package w2d3.client;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 55238;

    /**
     * Метод клиентской части. Слушает сокет от сервера. Закроется когда будет введен quit
     */
    public static void start () {
        try (
                Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
                PrintWriter out = new PrintWriter (new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                ) {
            System.out.println(in.readLine());
            String name = consoleReader.readLine();
            out.println(name);

            Thread readerThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.err.println("Connection the server is lost: " + e.getMessage());
                }
            });
            readerThread.setDaemon(true);
            readerThread.start();

            String input;
            while ((input = consoleReader.readLine()) != null) {
                out.println(input);
                if ("quit".equalsIgnoreCase(input.trim())) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Server connection error: " + e.getMessage());
        }
    }
}
