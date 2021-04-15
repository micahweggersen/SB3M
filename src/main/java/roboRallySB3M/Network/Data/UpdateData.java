package roboRallySB3M.Network.Data;

import java.util.HashMap;
import java.util.List;

public class UpdateData implements PayloadData {

    public List<PlayerData> playerData;
    public HashMap<String, LaserData> laserData;
    /**
     * @param playerData list of all players
     * @param laserData list of all lasers
     * @return list of all players in an UpdateData type.
     */
    public static UpdateData create(List<PlayerData> playerData, HashMap<String, LaserData> laserData) {
        return new UpdateData(playerData, laserData);
    }

    private UpdateData(List<PlayerData> playerData, HashMap<String, LaserData> laserData) {
        this.playerData = playerData;
        this.laserData = laserData;
    }

}
