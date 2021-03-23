package RoboRallySB3M.Network.Data;

import RoboRallySB3M.Direction;
import com.badlogic.gdx.math.Vector2;

public class PlayerData implements PayloadData {

    public String playerName;
    public Vector2 position;
    public Direction direction;

    public static PlayerData newPlayer(String playerName) {
        return new PlayerData(playerName, Vector2.Zero, Direction.NORTH);
    }

    public static PlayerData create(String playerName, Vector2 position, Direction direction) {
        return new PlayerData(playerName, position, direction);
    }

    private PlayerData(String playerName, Vector2 position, Direction direction) {
        this.playerName = playerName;
        this.position = position;
        this.direction = direction;
    }

}
