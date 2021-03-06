package RoboRallySB3M.Network.Data;

import RoboRallySB3M.Direction;
import RoboRallySB3M.Status;
import com.badlogic.gdx.math.Vector2;

public class PlayerData implements PayloadData {

    public String playerName;
    public Vector2 position;
    public Direction direction;
    public int turnOrder;
    public int damageToken;
    public int lifeTokens;
    public String playerTexture;
    public Status status;
    /**
     * @param playerName id - string
     * @return A new player with default values
     */
    public static PlayerData newPlayer(String playerName) {
        return new PlayerData(playerName, Vector2.Zero, Direction.NORTH, 0, 0 ,3, "src/assets/playerTexture/player.png", Status.ALIVE);
    }

    /**
     * @param playerName id - string
     * @param position coordinate - Vector2
     * @param direction North, west, east, south - Direction enum
     * @return an updated player or new
     */
    public static PlayerData create(String playerName, Vector2 position, Direction direction, int turnOrder, int damageToken,int lifeTokens, String playerTexture, Status status) {
        return new PlayerData(playerName, position, direction, turnOrder,damageToken, lifeTokens, playerTexture, status);
    }

    private PlayerData(String playerName, Vector2 position, Direction direction, int turnOrder, int damageToken, int lifeTokens, String playerTexture, Status status) {
        this.playerName = playerName;
        this.position = position;
        this.direction = direction;
        this.turnOrder = turnOrder;
        this.damageToken = damageToken;
        this.lifeTokens = lifeTokens;
        this.playerTexture = playerTexture;
        this.status = status;
    }
}
