package com.RoboRallySB3M.Network.Server;

import com.RoboRallySB3M.Direction;
import com.RoboRallySB3M.GameObjects.Board;
import com.RoboRallySB3M.Network.Data.LaserData;
import com.RoboRallySB3M.Network.Data.PlayerData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LaserServer implements Movement {

    private List<LaserData> laserLocation;
    private HashMap<String, LaserData> laserLocationDraw;

    public LaserServer() {

    }

    public void initialise() {
        laserLocation = new ArrayList<>();
        for (int x = 0; x < Board.boardLayer.getHeight(); x++) {
            for (int y = 0; y < Board.boardLayer.getWidth(); y++) {
                if (Board.walls.getCell(x, y) != null
                        && Board.walls.getCell(x, y).getTile().getProperties().containsKey("Laser")) {
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


    public void findLaser(List<PlayerData> players){

        for (LaserData v : laserLocation) {

            Direction dir = Direction.oppositeDirection(
                    Direction.stringToDirection(Board.walls.getCell(v.x, v.y).getTile().getProperties().get("direction").toString()));

            int x_change = Direction.changeInXdir(dir);
            int y_change = Direction.changeInYdir(dir);

            int x = v.x;
            int y = v.y;
            //Creates lasers Horizontal
            String placeholder = "laserH";

            if (Board.walls.getCell(v.x, v.y).getTile().getProperties().get("Laser").equals("H")) {
                while (canMove(dir, x, y)) {
                    //Stores location of drawn lasers - Key is x an y coordinate as a string with no space
                    String key = String.valueOf(x) + String.valueOf(y) + "H";

                    //If laser hits a player, set the laser draw value to null
                    if (playerWall(key, players)) placeholder = "null";

                    laserLocationDraw.put(key, LaserData.newLaser(placeholder, x, y));
                    if(x > Board.boardLayer.getWidth() || x < 0 ) break;
                    x += x_change;
                }
                addLaserOnWall(players, dir, x, y, placeholder, "H");

            }
            //Creates lasers Vertical
            placeholder = "laserV";

            if (Board.walls.getCell(v.x, v.y).getTile().getProperties().get("Laser").equals("V")) {
                while (canMove(dir, x, y)) {
                    //Stores location of drawn lasers - Key is x an y coordinate as a string with no space
                    String key = String.valueOf(x) + String.valueOf(y) + "V";

                    //If laser hits a player, set the laser draw value to null
                    if (playerWall(key, players)) placeholder = "null";

                    laserLocationDraw.put(key, LaserData.newLaser(placeholder, x, y));
                    if (y > Board.boardLayer.getHeight() || y < 0) break;
                    y += y_change;

                }
                addLaserOnWall(players, dir, x, y, placeholder, "V");
            }
        }
    }

    private void addLaserOnWall(List<PlayerData> players, Direction dir, int x, int y, String placeholder, String directionKey) {
        if(!canMove(dir, x, y)) {
            String key = String.valueOf(x) + String.valueOf(y) + directionKey;
            if (!playerWall(key, players)) {
                laserLocationDraw.put(key, LaserData.newLaser(placeholder, x, y));
            }
        }

    }

    private boolean playerWall(String key, List<PlayerData> players) {

        if(players == null || laserLocationDraw.size() <= 0) {
            return false;
        }

        for (PlayerData player : players) {
            if ((int) player.position.x == laserLocationDraw.get(key).x && (int) player.position.y == laserLocationDraw.get(key).y)
                return true;
        }
        return false;
    }
}
