package inf112.skeleton.app.Network.Data;

import java.util.List;

public class UpdateData implements PayloadData {

    public List<PlayerData> playerData;

    public static UpdateData create(List<PlayerData> playerData) {
        return new UpdateData(playerData);
    }

    private UpdateData(List<PlayerData> playerData) {
        this.playerData = playerData;
    }

}
