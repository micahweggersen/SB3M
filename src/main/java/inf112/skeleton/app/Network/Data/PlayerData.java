package inf112.skeleton.app.Network.Data;

public class PlayerData implements PayloadData {

    public String playerName;
    public float x;
    public float y;
    public int direction;

    public static PlayerData newPlayer(String playerName) {
        return new PlayerData(playerName, 0, 0, 0);
    }

    public static PlayerData create(String playerName, float x, float y, int direction) {
        return new PlayerData(playerName, x, y, direction);
    }

    private PlayerData(String playerName, float x, float y, int direction) {
        this.playerName = playerName;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}
