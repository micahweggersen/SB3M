package RoboRallySB3M.Network.Server;

import RoboRallySB3M.Direction;
import RoboRallySB3M.GameObjects.Board;
import RoboRallySB3M.Network.Data.LaserData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class LaserServer implements Movement {

    private List<LaserData> laserLocation;
    private HashMap<String, LaserData> laserLocationDraw = new HashMap<>();

    private static final String LASER = "Laser";
    private boolean first = false;

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

    public HashMap<String, LaserData> findLaserLocation(Collection<PlayerServer> players) {
        if (laserLocation == null) {
            initialise();
        }

        laserLocationDraw.clear();

        findLaser(players);

        return laserLocationDraw;
    }

    private void findLaser(Collection<PlayerServer> players) {
        first = true;
        for (LaserData v : laserLocation) {

            Direction direction = Direction.stringToDirection(
                    Board.walls.getCell(v.x, v.y).getTile().getProperties().get("direction").toString());
            if (direction == null) {
                return;
            }

            Direction oppositeDirection = Direction.oppositeDirection(direction);
            switch (oppositeDirection) {
                case NORTH:
                case SOUTH:
                    lasers(players, v, oppositeDirection, "laserV", "V");
                    break;
                case EAST:
                case WEST:
                    lasers(players, v, oppositeDirection, "laserH", "H");
                    break;
            }
        }
    }

    private void lasers(Collection<PlayerServer> players, LaserData v, Direction dir, String laserType, String pointer) {
        if (Board.walls.getCell(v.x, v.y).getTile().getProperties().get(LASER).equals(pointer)) {
            if(first) {
                laserLocationDraw.put(v.x + String.valueOf(v.y) + pointer, LaserData.newLaser(laserType, v.x, v.y));
                first = false;
            }
            while (canMove(dir, v.x, v.y)) {
                //If laser hits a player, don't draw more laser
                if (playerWall(players, v)) {
                    return;
                }
                //Stores location of drawn lasers - Key is x an y coordinate as a string with no space
                String key = v.x + String.valueOf(v.y) + pointer;
                laserLocationDraw.put(key, LaserData.newLaser(laserType, v.x, v.y));

                if (v.y > Board.boardLayer.getHeight() || v.y < 0 || v.x > Board.boardLayer.getHeight() || v.x < 0) {
                    break;
                }
                if (dir != null) {
                    if (laserType.equals("laserV")) {
                        v.y += Direction.changeInDirectionY(dir);
                    }
                    if (laserType.equals("laserH")) {
                        v.x += Direction.changeInDirectionX(dir);
                    }
                }
            }
            addLaserOnWall(players, dir, v.x, v.y, laserType, pointer);
        }
    }


    private void addLaserOnWall(Collection<PlayerServer> players, Direction dir, int x, int y, String placeholder, String directionKey) {
        if (!canMove(dir, x, y)) {
            String key = x + String.valueOf(y) + directionKey;
            LaserData v = LaserData.newLaser(placeholder, x, y);
            if (!playerWall(players, v)) {
                laserLocationDraw.put(key, v);
            }
        }

    }

    private boolean playerWall(Collection<PlayerServer> players, LaserData v) {

        if (players == null || laserLocationDraw.isEmpty()) return false;

        for (PlayerServer player : players) {
            if ((int) player.position.x == v.x && (int) player.position.y == v.y) {
                return true;
            }
        }
        return false;
    }
}