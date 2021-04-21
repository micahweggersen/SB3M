package RoboRallySB3M.Network.Data;

import java.util.HashMap;
import java.util.List;

public class UpdateData implements PayloadData {

    public List<LaserData> laserLocation;
    public List<PlayerData> playerData;
    public HashMap<String, LaserData> laserData;
    /**
     * @param playerData list of all players
     * @param laserData list of all lasers
     * @param laserLocation
     * @return list of all players in an UpdateData type.
     */
    public static UpdateData create(List<PlayerData> playerData, HashMap<String, LaserData> laserData, List<LaserData> laserLocation) {
        return new UpdateData(playerData, laserData, laserLocation);
    }

    private UpdateData(List<PlayerData> playerData, HashMap<String, LaserData> laserData,  List<LaserData> laserLocation) {
        this.playerData = playerData;
        this.laserData = laserData;
        this.laserLocation = laserLocation;
    }

}
