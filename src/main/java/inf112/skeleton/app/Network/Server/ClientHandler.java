package inf112.skeleton.app.Network.Server;

import inf112.skeleton.app.Board;
import inf112.skeleton.app.Cards.Cards;
import inf112.skeleton.app.Player;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

class ClientHandler extends Thread {
    private final Socket clientSocket;
    private final List<Player> playerList;
    private final List<Socket> connectedSockets;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket, List<Player> playerList, List<Socket> connectedSockets) {
        this.clientSocket = socket;
        this.playerList = playerList;
        this.connectedSockets = connectedSockets;
    }

    private Player findPlayer(String name) {
        for (Player player : playerList) {
            if (player.getName().equals(name)) {
                return player;
            }
        }

        return null;
    }

    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (".".equals(inputLine)) {
                    out.println("bye");
                    break;
                }

                if (inputLine.startsWith("quit")) {
                    // do quit

                    out.println(inputLine);
                } else if (inputLine.startsWith("login")) {
                    // do login

                    out.println(inputLine);
                } else {
                    // move
                    JSONObject obj = new JSONObject(inputLine);

                    String playerName = obj.getString("player");

                    Player player = findPlayer(playerName);
                    if (player == null) {
                        JSONObject returnObj = new JSONObject();
                        returnObj.put("error", "No player with name=" + playerName);
                        out.println(returnObj.toString());
                        continue;
                    }

                    Board.playerLayer.setCell((int) player.playerPosition.x, (int) player.playerPosition.y, null);

                    if (obj.has("cards")) {
                        JSONObject cardsJson = obj.getJSONObject("cards");
                        player.move(Cards.fromJson(cardsJson));
                    } else if (obj.has("playerX")) {
                        player.playerPosition.x = obj.getFloat("playerX");
                        player.playerPosition.y = obj.getFloat("playerY");
                    }

                    JSONObject returnObj = new JSONObject();
                    returnObj.put("player", playerName);
                    returnObj.put("playerX", player.playerPosition.x);
                    returnObj.put("playerY", player.playerPosition.y);out.println(returnObj.toString());
                }

                /* for (Socket socket : connectedSockets) {
                    PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
                    socketOut.println("yolo");
                } */
            }

            System.out.println("Closing connection, oh no!");

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}