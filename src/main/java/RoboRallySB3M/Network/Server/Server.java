package RoboRallySB3M.Network.Server;

import RoboRallySB3M.Network.Data.LaserData;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private final int serverPort;
    private final ConcurrentHashMap<String, PlayerServer> players = new ConcurrentHashMap<>();
    private final ConcurrentLinkedDeque<String> playersTexture = new ConcurrentLinkedDeque<>();

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
                new ClientHandler(serverSocket.accept(), players, playersTexture).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
