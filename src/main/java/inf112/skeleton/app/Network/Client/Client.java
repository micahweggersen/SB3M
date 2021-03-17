package inf112.skeleton.app.Network.Client;

import inf112.skeleton.app.Cards.Cards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client extends Thread {
    private final String ip;
    private final int port;

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String sendMessage(String msg) {
        out.println(msg);
        String resp = null;
        try {
            resp = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    public boolean startConnection() {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}


