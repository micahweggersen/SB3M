package inf112.skeleton.app.Network.Server;

import inf112.skeleton.app.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ConcurrentHashMap;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private final int serverPort;
    private final ConcurrentHashMap<String, Player> players = new ConcurrentHashMap<>();

    public Server(int serverPort) {
        this.serverPort = serverPort;
    }

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