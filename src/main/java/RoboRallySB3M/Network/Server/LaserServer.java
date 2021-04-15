package RoboRallySB3M.Network.Server;

import RoboRallySB3M.Direction;
import RoboRallySB3M.GameObjects.Board;
import RoboRallySB3M.Network.Data.LaserData;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class LaserServer {
    public TiledMapTileLayer.Cell laserH;
    public TiledMapTileLayer.Cell laserV;
    public TiledMapTileLayer.Cell laserVH;
    private List<LaserData> laserLocation;
    private HashMap<String, LaserData> laserLocationDraw;



    public HashMap<String, LaserData> findLaserLocation(ConcurrentHashMap<String, PlayerServer> players) {

        laserH = new TiledMapTileLayer.Cell();
        laserV = new TiledMapTileLayer.Cell();
        laserVH = new TiledMapTileLayer.Cell();

        laserLocation = new ArrayList<>();
        laserLocationDraw = new HashMap<>();
        //Finds laser walls on the board
        for (int x = 0; x < Board.boardLayer.getHeight(); x++) {
            for (int y = 0; y < Board.boardLayer.getWidth(); y++) {
                if (Board.walls.getCell(x, y) != null && Board.walls.getCell(x, y).getTile().getProperties().containsKey("Laser")) {
                    laserLocation.add(LaserData.newLaser("laser", x, y));
                }
            }
        }
        drawLaser(players);

        return laserLocationDraw;
    }


    public void drawLaser(ConcurrentHashMap<String, PlayerServer> players){

        for (LaserData v : laserLocation) {

            Direction dir = Direction.oppositeDirection(
                    Direction.stringToDirection(Board.walls.getCell(v.x, v.y).getTile().getProperties().get("direction").toString()));

            int x_change = Direction.changeInXdir(dir);
            int y_change = Direction.changeInYdir(dir);

            int x = v.x;
            int y = v.y;
            //Creates lasers Horizontal
            if (Board.walls.getCell(v.x, v.y).getTile().getProperties().get("Laser").equals("H")) {
                while (PlayerServer.canMove(dir, x, y)) {
                    //If laser hits a player, set the laser draw value to null
                    if (playerWall(x , y, players)) {
                        break;
                    }

                    //Stores location of drawn lasers - Key is x an y coordinate as a string with no space
                    String key = String.valueOf(x) + String.valueOf(y);
                    laserLocationDraw.put(key, LaserData.newLaser("laserH", x, y));
                    if(x > Board.boardLayer.getWidth() || x < 0 ) break;
                    x += x_change;
                }
            }
            //Creates lasers Vertical
            if (Board.walls.getCell(v.x, v.y).getTile().getProperties().get("Laser").equals("V")) {
                while (PlayerServer.canMove(dir, x, y)) {
                    //If laser hits a player, set the laser draw value to null
                    if (playerWall(x , y, players)) {
                        break;
                    }
                    //Stores location of drawn lasers - Key is x an y coordinate as a string with no space
                    String key = String.valueOf(x) + String.valueOf(y);
                    laserLocationDraw.put(key, LaserData.newLaser("laserV", x, y));

                    if (y > Board.boardLayer.getHeight() || y < 0) break;
                    y += y_change;

                }
            }
        }
    }

    private boolean playerWall(int x, int y, ConcurrentHashMap<String, PlayerServer> players) {

        if(players == null || laserLocationDraw.size() <= 0) {
            return false;
        }

        for (PlayerServer player : players.values()) {
            String key = String.valueOf(x) + String.valueOf(y);
            /*if ((int) player.position.x == laserLocationDraw.get(key).x && (int) player.position.y == laserLocationDraw.get(key).y) {
                return true;
            }*/
        }
        return false;
    }
}
