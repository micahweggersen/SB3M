package RoboRallySB3M.Network.Server;

import RoboRallySB3M.GameObjects.Board;
import RoboRallySB3M.Cards.Cards;
import RoboRallySB3M.Direction;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import RoboRallySB3M.Network.Data.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

class ClientHandler extends Thread implements Movement, GameLogic {
    private final Socket clientSocket;
    private final ConcurrentHashMap<String, PlayerServer> players;
    private final ConcurrentLinkedDeque<String> playersTexture;



    public ClientHandler(Socket socket, ConcurrentHashMap<String, PlayerServer> players, ConcurrentLinkedDeque<String> playersTexture) {
        this.clientSocket = socket;
        this.players = players;
        this.playersTexture = playersTexture;
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
            playerData.add(PlayerData.create(player.getName(), player.position.cpy(), player.getDirection(), player.getTurnOrder(), player.getDamageTokens(), player.getHealth(), player.getPlayerTexture()));
        }
        laserData = laser.findLaserLocation(playerData);

        return UpdateData.create(playerData, laserData);
    }

    /**
     * Thread that listens for input from clients and updates accordingly. Also gives output to update client.
     * This is were the player logic and movement is handled.
     */
    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            Object object;

            while ((object = in.readObject()) != null) {
                Payload payload = (Payload) object;
                if(payload.action == PayloadAction.QUIT) {
                    out.writeObject(Payload.create(PayloadAction.QUIT));
                    break;
                }

                switch (payload.action) {
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

                        turn(player, players);

                        out.writeObject(Payload.create(PayloadAction.SUCCESS));
                        break;
                    case UPDATE:
                        out.writeObject(Payload.create(PayloadAction.UPDATE, createUpdateData()));
                        break;
                    case JOIN:
                        PlayerData playerData = (PlayerData) payload.data;

                        if(playersTexture.isEmpty()) {
                            initialisePlayerTexture();
                        }

                        PlayerServer newPlayer = new PlayerServer(Direction.NORTH, playerData.playerName, players.size(), playerData.damageToken, playerData.health, playersTexture.pop());
                        newPlayer.position = new Vector2(players.size(), 0);

                        players.put(playerData.playerName, newPlayer);

                        out.writeObject(Payload.create(PayloadAction.SUCCESS));
                        break;
                    case DISCONNECT:
                        break;
                    default:
                }
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initialisePlayerTexture() {
        playersTexture.add("src/assets/player.png");
        playersTexture.add("src/assets/playerYellow.png");
    }
}