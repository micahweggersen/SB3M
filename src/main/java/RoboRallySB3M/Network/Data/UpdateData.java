package RoboRallySB3M.Network.Data;

import java.util.List;

public class UpdateData implements PayloadData {

    public List<PlayerData> playerData;

    /**
     * @param playerData list of all players
     * @return list of all players in an UpdateData type.
     */
    public static UpdateData create(List<PlayerData> playerData) {
        return new UpdateData(playerData);
    }

    private UpdateData(List<PlayerData> playerData) {
        this.playerData = playerData;
    }

}
