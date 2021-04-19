package RoboRallySB3M.Network.Server;

import RoboRallySB3M.GameObjects.Board;
import RoboRallySB3M.Direction;
import RoboRallySB3M.Network.Data.LaserData;
import RoboRallySB3M.Network.Data.PlayerData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LaserServer implements Movement {

    private List<LaserData> laserLocation;
    private HashMap<String, LaserData> laserLocationDraw = new HashMap<>();;
    private static final String LASER = "Laser";

    private void initialise() {
        laserLocation = new ArrayList<>();
        for (int x = 0; x < Board.boardLayer.getHeight(); x++) {
            for (int y = 0; y < Board.boardLayer.getWidth(); y++) {
                if (Board.walls.getCell(x, y) != null
                        && Board.walls.getCell(x, y).getTile().getProperties().containsKey(LASER)) {
                    laserLocation.add(LaserData.newLaser("laser", x, y));
                }
            }
        }
    }

    public HashMap<String, LaserData> findLaserLocation(List<PlayerData> players) {
        if(laserLocation == null) {
            initialise();
        }

        if(laserLocationDraw != null) laserLocationDraw.clear();

        findLaser(players);

        return laserLocationDraw;
    }

    private void findLaser(List<PlayerData> players){
        int x;
        int y;

        for (LaserData v : laserLocation) {

            Direction dir = Direction.oppositeDirection(
                    Objects.requireNonNull(Direction.stringToDirection(
                            Board.walls.getCell(v.x, v.y).getTile().getProperties().get("direction").toString())));
            x = v.x;
            y = v.y;
            //Creates lasers Horizontal
            lasers(players, x, y, v, dir, "laserH", "H");
            //Creates lasers Vertical

            lasers(players, x, y, v, dir, "laserV", "V");
        }
    }

    private void lasers(List<PlayerData> players, int x, int y, LaserData v, Direction dir,String placeholder, String pointer) {
        if (Board.walls.getCell(v.x, v.y).getTile().getProperties().get(LASER).equals(pointer)) {
            while (canMove(dir, x, y)) {
                //Stores location of drawn lasers - Key is x an y coordinate as a string with no space
                String key = x + String.valueOf(y) + pointer;
                //If laser hits a player, set the laser draw value to null
                if (playerWall(key, players)) placeholder = "null";
                laserLocationDraw.put(key, LaserData.newLaser(placeholder, x, y));
                if (y > Board.boardLayer.getHeight() || y < 0) break;
                if (dir != null) {
                    if(placeholder.equals("laserV")) {
                        y += Direction.changeInDirectionY(dir);
                    }
                    if(placeholder.equals("laserH")) {
                        x += Direction.changeInDirectionX(dir);
                    }
                }
            }
            addLaserOnWall(players, dir, x, y, placeholder, pointer);
        }
    }


    private void addLaserOnWall(List<PlayerData> players, Direction dir, int x, int y, String placeholder, String directionKey) {
        if(!canMove(dir, x, y)) {
            String key = x + String.valueOf(y) + directionKey;
            if (!playerWall(key, players)) {
                laserLocationDraw.put(key, LaserData.newLaser(placeholder, x, y));
            }
        }

    }

    private boolean playerWall(String key, List<PlayerData> players) {

        if(players == null || laserLocationDraw.size() <= 0 || laserLocationDraw.get(key) == null) return false;

        for (PlayerData player : players) {
            if ((int) player.position.x == laserLocationDraw.get(key).x && (int) player.position.y == laserLocationDraw.get(key).y)
                return true;
        }
        return false;
    }
}
