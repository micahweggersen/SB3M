package RoboRallySB3M.GameObjects;

import RoboRallySB3M.Direction;
import RoboRallySB3M.GameObjects.Data.LaserData;
import RoboRallySB3M.Network.Data.PlayerData;
import RoboRallySB3M.Network.Server.PlayerServer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.*;

public class Laser {

    public static TiledMapTileLayer.Cell laserH;
    public static TiledMapTileLayer.Cell laserV;
    public static TiledMapTileLayer.Cell laserVH;
    private static List<LaserData> laserLocation;
    private static HashMap<String, LaserData> laserLocationDraw;


    /**
     * Creating laser from tileset and initializing list for storing laser locations.
     * Finds laser walls on the board and stores there location.
     */
    public static void createLaser() {

        laserH = new TiledMapTileLayer.Cell();
        laserV = new TiledMapTileLayer.Cell();
        laserVH = new TiledMapTileLayer.Cell();

        Texture texture = new Texture("src/assets/tiles.png"); //henter ut segment av et bilde og ikke tile verider derfor kommer ikke verdiene til tile med
        TextureRegion[][] laserFig = TextureRegion.split(texture, 300, 300);
        laserH.setTile(new StaticTiledMapTile(laserFig[4][6]));
        laserV.setTile(new StaticTiledMapTile(laserFig[5][6]));
        laserVH.setTile(new StaticTiledMapTile(laserFig[4][7]));
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
    }

    /**
     * @param playerData List of all current player positions
     */
    public static void drawLaser(List<PlayerData> playerData){

        for (LaserData v : laserLocation) {

            Direction dir = Direction.oppositeDirection(
                    Direction.stringToDirection(Board.walls.getCell(v.x, v.y).getTile().getProperties().get("direction").toString()));

            int x_change = Direction.changeInXdir(dir);
            int y_change = Direction.changeInYdir(dir);

            int x = v.x;
            int y = v.y;
            //Draws lasers Horizontal
            TiledMapTileLayer.Cell placeholder = laserH;
            if (Board.walls.getCell(v.x, v.y).getTile().getProperties().get("Laser").equals("H")) {
                Board.laserHorizontal.setCell(x, y, laserH);
                while (PlayerServer.canMove(dir, x, y)) {
                    //If laser hits a player, set the laser draw value to null
                    if (playerWall(x , y, playerData)) {
                        placeholder = null;
                    }
                    Board.laserHorizontal.setCell(x+x_change, y, placeholder);

                    //Stores location of drawn lasers - Key is x an y coordinate as a string with no space
                    String key = String.valueOf(x) + String.valueOf(y);
                    laserLocationDraw.put(key, LaserData.newLaser("laserH", x, y));
                    if(x > Board.boardLayer.getWidth() || x < 0 ) break;
                    x += x_change;
                }
            }
            //Draws lasers Vertical
            placeholder = laserV;
            if (Board.walls.getCell(v.x, v.y).getTile().getProperties().get("Laser").equals("V")) {
                Board.laserVertical.setCell(x, y, laserV);
                while (PlayerServer.canMove(dir, x, y)) {
                    //If laser hits a player, set the laser draw value to null
                    if (playerWall(x , y, playerData)) {
                        placeholder = null;
                    }
                    Board.laserVertical.setCell(x, y + y_change, placeholder);
                    //Stores location of drawn lasers - Key is x an y coordinate as a string with no space
                    String key = String.valueOf(x) + String.valueOf(y);
                    laserLocationDraw.put(key, LaserData.newLaser("laserV", x, y));

                    if (y > Board.boardLayer.getHeight() || y < 0) break;
                    y += y_change;

                }
            }
        }
    }

    /**
     * @param x coordinate
     * @param y coordinate
     * @param playerList list of current players
     * @return if player location is the same as drawn laser location then return true - this stops the laser form being drawn
     */
    private static boolean playerWall(int x, int y, List<PlayerData> playerList) {

        if(playerList == null || laserLocationDraw.size() <= 0) {
            return false;
        }

        for (PlayerData player : playerList) {
            String key = String.valueOf(x) + String.valueOf(y);
            if ((int) player.position.x == laserLocationDraw.get(key).x && (int) player.position.y == laserLocationDraw.get(key).y) {
                return true;
            }
        }
        return false;
    }

}