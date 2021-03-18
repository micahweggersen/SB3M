package inf112.skeleton.app.Network.Server;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Board;
import inf112.skeleton.app.Cards.Cards;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.Network.Data.*;
import inf112.skeleton.app.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class ClientHandler extends Thread {
    private final Socket clientSocket;
    private final ConcurrentHashMap<String, Player> players;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ClientHandler(Socket socket, ConcurrentHashMap<String, Player> players) {
        this.clientSocket = socket;
        this.players = players;
    }

    private Player findPlayer(String name) {
        return players.get(name);
    }

    private UpdateData createUpdateData() {
        List<PlayerData> data = new ArrayList<>(players.size());

        for (Player player : players.values()) {
            data.add(PlayerData.create(player.getName(), player.playerPosition.x, player.playerPosition.y, player.getDirection()));
        }

        return UpdateData.create(data);
    }

    public void run() {
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            Object object;

            loop:
            while ((object = in.readObject()) != null) {
                Payload payload = (Payload) object;

                switch (payload.action) {
                    case QUIT:
                        out.writeObject(Payload.create(PayloadAction.QUIT));
                        break loop;
                    case CARD:
                        MoveCardData dataCard = (MoveCardData) payload.data;
                        String cardID = dataCard.cardID;
                        int cardDir = dataCard.cardDir;
                        int cardMom = dataCard.cardMom;
                        int cardPV = dataCard.cardPV;
                        String playerNameCard = dataCard.playerName;

                        Player playerCard = findPlayer(playerNameCard);
                        if (playerCard == null) {
                            continue;
                        }
                        playerCard.move(new Cards(cardPV, cardID, cardDir, cardMom));

                        out.writeObject(Payload.create(PayloadAction.SUCCESS));

                        break;

                    case MOVE:
                        MoveData data = (MoveData) payload.data;
                        int keycode = data.keyCode;
                        String playerName = data.playerName;

                        Player player = findPlayer(playerName);
                        if (player == null) {
                            continue;
                        }

                        Board.playerLayer.setCell((int) player.playerPosition.x, (int) player.playerPosition.y, null);

                        switch (keycode) {
                            case Input.Keys.NUM_1:
                                player.move(new Cards(0, "Move One", 0, 1));
                                break;
                            case Input.Keys.NUM_2:
                                player.move(new Cards(0, "Move Two", 0, 2));
                                break;
                            case Input.Keys.NUM_3:
                                player.move(new Cards(0, "Move Three", 0, 3));
                                break;
                            case Input.Keys.NUM_4:
                                player.move(new Cards(0, "Rotate Left", 3, 0));
                                break;
                            case Input.Keys.NUM_5:
                                player.move(new Cards(0, "Rotate Right", 1, 0));
                                break;
                            case Input.Keys.NUM_6:
                                player.move(new Cards(0, "U-Turn", 2, 0));
                                break;
                            case Input.Keys.NUM_7:
                                player.move(new Cards(0, "Back Up", 0, -1));
                                break;
                            case Input.Keys.LEFT:
                                if (player.canMove(Direction.WEST)) {
                                    player.playerPosition.x -= 1;
                                }
                                break;
                            case Input.Keys.RIGHT:
                                if (player.canMove(Direction.EAST)) {
                                    player.playerPosition.x += 1;
                                }
                                break;
                            case Input.Keys.UP:
                                if (player.canMove(Direction.NORTH)) {
                                    player.playerPosition.y += 1;
                                }
                                break;
                            case Input.Keys.DOWN:
                                if (player.canMove(Direction.SOUTH)) {
                                    player.playerPosition.y -= 1;
                                }
                                break;
                        }

                        out.writeObject(Payload.create(PayloadAction.SUCCESS));
                        break;
                    case UPDATE:
                        out.writeObject(Payload.create(PayloadAction.UPDATE, createUpdateData()));
                        break;
                    case JOIN:
                        PlayerData playerData = (PlayerData) payload.data;

                        Player newPlayer = new Player(0, playerData.playerName);
                        newPlayer.playerPosition = new Vector2(players.size(), 0);

                        players.put(playerData.playerName, newPlayer);

                        out.writeObject(Payload.create(PayloadAction.SUCCESS));
                        break;
                }
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}