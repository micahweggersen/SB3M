package RoboRallySB3M.Network.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ConcurrentHashMap;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private final int serverPort;
    private final ConcurrentHashMap<String, PlayerServer> players = new ConcurrentHashMap<>();

    public Server(int serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * Runs the server and ads new clients as a new thread - ClientHandler.
     */
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                new ClientHandler(serverSocket.accept(), players).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
