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
    private HashMap<String, LaserData> laserLocationDraw;
    private static final String LASER = "Laser";

    public LaserServer() {
        //No values to store
    }

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

        laserLocationDraw = new HashMap<>();
        findLaser(players);

        return laserLocationDraw;
    }

    private void findLaser(List<PlayerData> players){

        for (LaserData v : laserLocation) {

            Direction dir = Direction.oppositeDirection(
                    Objects.requireNonNull(Direction.stringToDirection(
                            Board.walls.getCell(v.x, v.y).getTile().getProperties().get("direction").toString())));

            int xChange = 0;
            int yChange = 0;
            if (dir != null) {
                xChange = Direction.changeInDirectionX(dir);
                yChange = Direction.changeInDirectionY(dir);
            }

            int x = v.x;
            int y = v.y;

            //Creates lasers Horizontal
            String placeholder = "laserH";

            if (Board.walls.getCell(v.x, v.y).getTile().getProperties().get(LASER).equals("H")) {
                while (canMove(dir, x, y)) {
                    //Stores location of drawn lasers - Key is x an y coordinate as a string with no space
                    String key = x + String.valueOf(y) + "H";

                    //If laser hits a player, set the laser draw value to null
                    if (playerWall(key, players)) placeholder = "null";

                    laserLocationDraw.put(key, LaserData.newLaser(placeholder, x, y));
                    if(x > Board.boardLayer.getWidth() || x < 0 ) break;
                    x += xChange;
                }
                addLaserOnWall(players, dir, x, y, placeholder, "H");

            }
            //Creates lasers Vertical
            placeholder = "laserV";

            if (Board.walls.getCell(v.x, v.y).getTile().getProperties().get(LASER).equals("V")) {
                while (canMove(dir, x, y)) {
                    //Stores location of drawn lasers - Key is x an y coordinate as a string with no space
                    String key = x + String.valueOf(y) + "V";

                    //If laser hits a player, set the laser draw value to null
                    if (playerWall(key, players)) placeholder = "null";

                    laserLocationDraw.put(key, LaserData.newLaser(placeholder, x, y));
                    if (y > Board.boardLayer.getHeight() || y < 0) break;
                    y += yChange;
                }
                addLaserOnWall(players, dir, x, y, placeholder, "V");
            }
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
