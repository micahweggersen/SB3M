package RoboRallySB3M.Network.Server;

import RoboRallySB3M.GameObjects.Board;
import RoboRallySB3M.Cards.Cards;
import RoboRallySB3M.Direction;
import RoboRallySB3M.Network.Data.*;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class ClientHandler extends Thread {
    private final Socket clientSocket;
    private final ConcurrentHashMap<String, PlayerServer> players;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ClientHandler(Socket socket, ConcurrentHashMap<String, PlayerServer> players) {
        this.clientSocket = socket;
        this.players = players;
    }

    /**
     * @param name playerName
     * @return player
     */
    private PlayerServer findPlayer(String name) {
        return players.get(name);
    }

    /**
     * @return Updated values to playerData
     */
    private UpdateData createUpdateData() {
        List<PlayerData> playerData = new ArrayList<>(players.size());
        HashMap<String, LaserData> laserData;

        LaserServer laser = new LaserServer();
        laserData = laser.findLaserLocation(players);

        for (PlayerServer player : players.values()) {
            playerData.add(PlayerData.create(player.getName(), player.position.cpy(), player.getDirection(), player.getTurnOrder()));
        }
        return UpdateData.create(playerData, laserData);
    }

    /**
     * Thread that listens for input from clients and updates accordingly. Also gives output to update client.
     * This is were the player logic and movement is handled.
     */
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
                        Direction cardDir = dataCard.cardDir;
                        int cardMom = dataCard.cardMom;
                        int cardPV = dataCard.cardPV;
                        String playerNameCard = dataCard.playerName;

                        PlayerServer playerCard = findPlayer(playerNameCard);
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

                        PlayerServer player = findPlayer(playerName);
                        if (player == null) {
                            continue;
                        }
                        if(player.getTurnOrder() != 0) {
                            System.out.println("Not your turn!");
                            out.writeObject(Payload.create(PayloadAction.NOT_YOUR_TURN));
                            break;
                        }

                        Board.playerLayer.setCell((int) player.position.x, (int) player.position.y, null);

                        switch (keycode) {
                            case Input.Keys.NUM_1:
                                player.move(new Cards(0, "Move One", Direction.NORTH, 1));
                                break;
                            case Input.Keys.NUM_2:
                                player.move(new Cards(0, "Move Two", Direction.NORTH, 2));
                                break;
                            case Input.Keys.NUM_3:
                                player.move(new Cards(0, "Move Three", Direction.NORTH, 3));
                                break;
                            case Input.Keys.NUM_4:
                                player.move(new Cards(0, "Rotate Left", Direction.EAST, 0));
                                break;
                            case Input.Keys.NUM_5:
                                player.move(new Cards(0, "Rotate Right", Direction.WEST, 0));
                                break;
                            case Input.Keys.NUM_6:
                                player.move(new Cards(0, "U-Turn", Direction.SOUTH, 0));
                                break;
                            case Input.Keys.NUM_7:
                                player.move(new Cards(0, "Back Up", Direction.NORTH, -1));
                                break;
                            case Input.Keys.LEFT:
                                if (player.canMove(Direction.WEST, (int) player.position.x, (int) player.position.y)) {
                                    player.position.x -= 1;
                                }
                                break;
                            case Input.Keys.RIGHT:
                                if (player.canMove(Direction.EAST, (int) player.position.x, (int) player.position.y)) {
                                    player.position.x += 1;
                                }
                                break;
                            case Input.Keys.UP:
                                if (player.canMove(Direction.NORTH, (int) player.position.x, (int) player.position.y)) {
                                    player.position.y += 1;
                                }
                                break;
                            case Input.Keys.DOWN:
                                if (player.canMove(Direction.SOUTH, (int) player.position.x, (int) player.position.y)) {
                                    player.position.y -= 1;
                                }
                                break;
                        }
                        turnHandling(player);
                        out.writeObject(Payload.create(PayloadAction.SUCCESS));
                        break;
                    case UPDATE:
                        out.writeObject(Payload.create(PayloadAction.UPDATE, createUpdateData()));
                        break;
                    case JOIN:
                        PlayerData playerData = (PlayerData) payload.data;

                        PlayerServer newPlayer = new PlayerServer(Direction.NORTH, playerData.playerName, players.size());
                        newPlayer.position = new Vector2(players.size(), 0);

                        players.put(playerData.playerName, newPlayer);

                        out.writeObject(Payload.create(PayloadAction.SUCCESS));
                        break;
                    case DISCONNECT:
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

    private void turnHandling(PlayerServer player) {
        player.setTurnOrder(player.getTurnOrder()-1);

        for (PlayerServer p: players.values()) {
            if (!player.getName().equals(p.getName())) {
                p.setTurnOrder(p.getTurnOrder()-1);
            }
            if(p.getTurnOrder() < 0) {
                p.setTurnOrder(players.size()-1);
            }
            System.out.println(p.getTurnOrder());
        }
    }
}