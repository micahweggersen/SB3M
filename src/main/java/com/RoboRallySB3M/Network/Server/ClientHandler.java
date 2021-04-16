package com.RoboRallySB3M.Network.Server;

import com.RoboRallySB3M.GameObjects.Board;
import com.RoboRallySB3M.Cards.Cards;
import com.RoboRallySB3M.Direction;
import com.RoboRallySB3M.Network.Data.*;
import com.badlogic.gdx.math.Vector2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class ClientHandler extends Thread implements Movement, GameLogic {
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

        for (PlayerServer player : players.values()) {
            playerData.add(PlayerData.create(player.getName(), player.position.cpy(), player.getDirection(), player.getTurnOrder(), player.getDamageToken(), player.getHealth()));
        }
        laserData = laser.findLaserLocation(playerData);

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
                            break;
                        }

                        Board.playerLayer.setCell((int) player.position.x, (int) player.position.y, null);

                        moveByKeyPress(keycode, player);
                        orderHandling(player, players);
                        turnHandling(players);

                        out.writeObject(Payload.create(PayloadAction.SUCCESS));
                        break;
                    case UPDATE:
                        out.writeObject(Payload.create(PayloadAction.UPDATE, createUpdateData()));
                        break;
                    case JOIN:
                        PlayerData playerData = (PlayerData) payload.data;

                        PlayerServer newPlayer = new PlayerServer(Direction.NORTH, playerData.playerName, players.size(), 10, 10);
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
}