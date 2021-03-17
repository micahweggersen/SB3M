package inf112.skeleton.app.Network.Server;

import inf112.skeleton.app.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private final int serverPort;
    private final List<Player> playerList;
    private final List<Socket> connectedSockets;

    public Server(int serverPort, List<Player> playerList) {
        this.serverPort = serverPort;
        this.playerList = playerList;
        this.connectedSockets = new ArrayList<>();
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
                Socket socket = serverSocket.accept();
                connectedSockets.add(socket);
                new ClientHandler(socket, playerList, connectedSockets).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
