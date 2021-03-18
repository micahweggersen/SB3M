package RoboRallySB3M.Network.Data;

public class MoveData implements PayloadData {

    public int keyCode;
    public String playerName;

    public static MoveData create(int keyCode, String playerName) {
        return new MoveData(keyCode, playerName);
    }

    private MoveData(int keyCode, String playerName) {
        this.keyCode = keyCode;
        this.playerName = playerName;
    }

}
