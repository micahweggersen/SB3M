package roboRallySB3M.Network.Data;

import roboRallySB3M.Direction;
import com.badlogic.gdx.math.Vector2;

public class PlayerData implements PayloadData {

    public String playerName;
    public Vector2 position;
    public Direction direction;
    public int turnOrder;

    /**
     * @param playerName id - string
     * @return A new player with default values
     */
    public static PlayerData newPlayer(String playerName) {
        return new PlayerData(playerName, Vector2.Zero, Direction.NORTH, 0);
    }

    /**
     * @param playerName id - string
     * @param position coordinate - Vector2
     * @param direction North, west, east, south - Direction enum
     * @return an updated player or new
     */
    public static PlayerData create(String playerName, Vector2 position, Direction direction, int turnOrder) {
        return new PlayerData(playerName, position, direction, turnOrder);
    }

    private PlayerData(String playerName, Vector2 position, Direction direction, int turnOrder) {
        this.playerName = playerName;
        this.position = position;
        this.direction = direction;
        this.turnOrder = turnOrder;
    }

}
