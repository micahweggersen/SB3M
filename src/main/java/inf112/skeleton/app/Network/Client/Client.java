package inf112.skeleton.app.Network.Client;

import inf112.skeleton.app.Cards.Cards;
import inf112.skeleton.app.Network.Data.Payload;
import inf112.skeleton.app.Network.Data.PayloadAction;
import inf112.skeleton.app.Network.Data.PlayerData;
import inf112.skeleton.app.Network.Data.UpdateData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Queue;


public class Client extends Thread {
    private final String ip;
    private final int port;
    private UpdateListener listener;

    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private List<Cards> dealtCards;
    private Queue<Cards> chosenCards;

    public Client(String ip, int port, UpdateListener listener) {
        this.ip = ip;
        this.port = port;
        this.listener = listener;
    }

    /**
     * @param payload data package sending from client to server
     * @return data package output to server
     */
    public Payload sendPayload(Payload payload) {
        try {
            out.writeObject(payload);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Payload resp = null;
        try {
            resp = (Payload) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resp;
    }

    public boolean startConnection() {
        try {
            clientSocket = new Socket(ip, port);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
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

    @Override
    public void run() {
        super.run();

        if (listener == null) {
            return;
        }

        try {
            while (true) {
                out.writeObject(Payload.create(PayloadAction.UPDATE));

                Payload payload = (Payload) in.readObject();
                if (payload.action == PayloadAction.UPDATE) {
                    UpdateData data = (UpdateData) payload.data;

                    listener.update(data.playerData);
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public interface UpdateListener {
        void update(List<PlayerData> playerData);
    }
}


